package app.mapper;

import app.dto.PostRequest;
import app.dto.UserRequest;
import app.dto.UserResponse;
import app.entity.Post;
import app.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRequest request) {
        return new User(request.name(), request.email());
    }

    public UserResponse toDto(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }
}