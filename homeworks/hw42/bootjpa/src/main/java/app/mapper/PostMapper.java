package app.mapper;

import app.dto.PostRequest;
import app.dto.PostResponse;
import app.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {
    public Post toEntity(PostRequest request) {
        return new Post(request.title(), request.content());
    }

    public PostResponse toDto(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getContent(), post.getUser().getId());
    }
}