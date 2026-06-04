package com.example.Spring_Security.service.impl;

import com.example.Spring_Security.dto.UserDto;
import com.example.Spring_Security.entity.Role;
import com.example.Spring_Security.entity.User;
import com.example.Spring_Security.repository.RoleRepository;
import com.example.Spring_Security.repository.UserRepository;
import com.example.Spring_Security.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
//        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());

        user.setEmail(userDto.getEmail());

        // Шифрування паролю при інтеграції Spring Security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if (role == null) {
            role = checkRoleExist();
        }
        user.setRoles(List.of(role)); //судячи по всьому тут приділяється завжди всім адмін роль, дивних підхід але так було в лекцїі
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
//        String[] name = user.getName().split(" ");
//        userDto.setFirstName(name[0]);
//        userDto.setLastName(name[1]);

        userDto.setName(user.getName());
        userDto.setPhone(user.getPhone());

        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
