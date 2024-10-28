package com.evently.user.service;

import com.evently.user.dao.UserProfileRepository;
import com.evently.user.dto.UserDto;
import com.evently.user.dto.UserProfileDto;
import com.evently.user.entity.User;
import com.evently.user.dao.UserRepository;
import com.evently.user.entity.UserProfile;
import com.evently.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;

    @Cacheable(value = "users", key = "#id")
    public UserDto getUserById(String id) {
        final User user = userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        return UserDto.builder()
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }

    @Cacheable(value = "user_profiles", key = "#id")
    public UserProfileDto getUserProfileById(String id) {
        final UserProfile userProfile = userProfileRepository
                .findByUserId(id)
                .orElseThrow(UserNotFoundException::new);

        return UserProfileDto.builder()
                .id(userProfile.getUser().getId())
                .name(userProfile.getUser().getName())
                .username(userProfile.getUser().getUsername())
                .city(userProfile.getCity())
                .pronouns(userProfile.getPronouns())
                .description(userProfile.getDescription())
                .birthdate(userProfile.getBirthdate())
                .friendsCount(userProfile.getFriendsCount())
                .build();
    }
}