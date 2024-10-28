package com.evently.user.controller;

import com.evently.user.dto.UserDto;
import com.evently.user.dto.UserProfileDto;
import com.evently.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/v1/users/{id}")
    public UserDto getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/v1/users/{id}/profile")
    public UserProfileDto getUserProfile(@PathVariable String id) {
        return userService.getUserProfileById(id);
    }
}
