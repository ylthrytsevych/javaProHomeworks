package app.service;

import app.entity.User;
import app.exception.NotUniqueUserException;
import app.mapper.UserMapper;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.dto.UserRequest;
import app.dto.UserResponse;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService (final UserRepository userRepository, UserMapper userMapper){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }


    @Transactional
    public UserResponse createUser(UserRequest request) {
        validateUser(request); // Перевірка на унікальність email

        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsersByName(String name) {
        return userRepository.findByName(name).stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsersByEmailDomain(String domain) {
        return userRepository.findByEmailEndingWith(domain).stream()
                .map(userMapper::toDto)
                .toList();
    }

    private void validateUser(UserRequest request) {
        // Перевіряємо, чи вже є користувач з таким email у базі
        boolean emailExists = userRepository.findByEmailEndingWith(request.email()).stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(request.email()));
        if (emailExists) {
            throw new NotUniqueUserException("Користувач з email " + request.email() + " вже існує!");
        }
    }

}
