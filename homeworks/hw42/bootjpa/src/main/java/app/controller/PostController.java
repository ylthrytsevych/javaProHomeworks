package app.controller;

import app.dto.PostRequest;
import app.dto.PostResponse;
import app.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {
    private final PostService postService;

    @PostMapping
    public PostResponse createPost(@RequestBody PostRequest request) {
        return postService.createPost(request);
    }

    @GetMapping("/user/{userId}")
    public List<PostResponse> getPostsByUserId(@PathVariable("userId") Long userId) {
        return postService.getPostsByUserId(userId);
    }
}
