package app.service;

import app.dto.PostRequest;
import app.dto.PostResponse;
import app.dto.UserRequest;
import app.entity.Post;
import app.entity.User;
import app.exception.UserNotFoundException;
import app.mapper.PostMapper;
import app.repository.PostRepository;
import app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostMapper postMapper;

    @Transactional
    public PostResponse createPost(PostRequest request) {
        // Шукаємо користувача. Якщо немає — кидаємо помилку
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new UserNotFoundException("Користувача з ID=" + request.userId() + " не знайдено"));

        Post post = postMapper.toEntity(request);
        user.addPost(post); // Зберігаємо двосторонній зв'язок

        Post savedPost = postRepository.save(post);
        return postMapper.toDto(savedPost);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Користувача з ID=" + userId + " не знайдено");
        }
        return postRepository.findByUserId(userId).stream()
                .map(postMapper::toDto)
                .toList();
    }


}
