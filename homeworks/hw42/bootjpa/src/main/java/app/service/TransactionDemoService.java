package app.service;

import app.dto.PostRequest;
import app.dto.UserRequest;
import app.entity.Post;
import app.entity.User;
import app.mapper.PostMapper;
import app.mapper.UserMapper;
import app.repository.PostRepository;
import app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor

public class TransactionDemoService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final UserMapper userMapper;
    private final PostMapper postMapper;

    @Transactional
    public void executeRollbackTest(UserRequest userRequest, PostRequest postRequest) {

        User user = userMapper.toEntity(userRequest);
        User savedUser = userRepository.save(user);

        Post post = postMapper.toEntity(postRequest);
        savedUser.addPost(post);
        postRepository.save(post);

        // Викидаємо помилку, щоб перевірити відкат transcational
       throw new RuntimeException("Симульована помилка! Транзакція має відкотитися.");
    }
}
