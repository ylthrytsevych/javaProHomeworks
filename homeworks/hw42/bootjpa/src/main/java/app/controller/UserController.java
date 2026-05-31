package app.controller;

import app.dto.UserRequest;
import app.dto.UserResponse;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        return service.createUser(request);
    }

    @GetMapping("/search")
    public List<UserResponse> getUserByName(@RequestParam("name") String name) {
        return service.getUsersByName(name);
    }

    @GetMapping("/domain")
    public List<UserResponse> getUsersByDomain(@RequestParam("domain") String domain) {
        return service.getUsersByEmailDomain(domain);
    }

}
