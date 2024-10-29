package com.evently.user.controller;

import com.evently.user.dto.*;
import com.evently.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/v1/registration")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RegistrationDto startRegistration(@RequestBody StartRegistrationRequestDto request) {
        return userService.startRegistration(request);
    }

    @PostMapping("/v1/users")
    public void createUser(@RequestBody CreateUserRequestDto request) {
        userService.createUser(request);
    }

    @GetMapping("/v1/users/{id}")
    public UserDto getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/v1/users/{id}/profile")
    public ProfileDto getProfile(@PathVariable String id) {
        return userService.getUserProfileById(id);
    }

    @GetMapping("/v1/users/{id}/friends")
    public List<UserDto> getFriends(@PathVariable String id,
                                    @RequestParam int pageNumber,
                                    @RequestParam int pageSize) {
        return userService.getFriends(id, pageNumber, pageSize);
    }
}
