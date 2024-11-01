package com.evently.user.controller;

import com.evently.user.dto.*;
import com.evently.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/v1/registrations")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RegistrationDto startRegistration(@RequestBody @Valid StartRegistrationRequestDto request) {
        return userService.startRegistration(request);
    }

    @PostMapping("/v1/users")
    public UserDto createUser(@RequestBody @Valid CreateUserRequestDto request) {
        return userService.createUser(request);
    }

    @GetMapping("/v1/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping("/v1/users/{id}/profile")
    @ResponseStatus(HttpStatus.OK)
    public ProfileDto getProfile(@PathVariable UUID id) {
        return userService.getUserProfileById(id);
    }

    @GetMapping("/v1/users/{id}/friends")
    public List<UserDto> getFriends(@PathVariable UUID id,
                                    @RequestParam int pageNumber,
                                    @RequestParam int pageSize) {
        return userService.getFriends(id, pageNumber, pageSize);
    }
}
