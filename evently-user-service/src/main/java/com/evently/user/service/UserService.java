package com.evently.user.service;

import com.evently.user.dao.ProfileRepository;
import com.evently.user.dao.RelationshipRepository;
import com.evently.user.dto.*;
import com.evently.user.entity.Profile;
import com.evently.user.entity.User;
import com.evently.user.dao.UserRepository;
import com.evently.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final RelationshipRepository relationshipRepository;

    @Mapper
    interface UserMapper {

        UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

        UserDto toDto(User user);
    }

    @Mapper(componentModel = "spring")
    interface ProfileMapper {

        ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

        @Mapping(source = "user.username", target = "username")
        ProfileDto toDto(Profile profile);
    }

    @Cacheable(value = "evently_users", key = "#id")
    public UserDto getUserById(String id) {
        final User user = userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        return UserMapper.INSTANCE.toDto(user);
    }

    @Cacheable(value = "evently_profiles", key = "#id")
    public ProfileDto getUserProfileById(String id) {
        final Profile profile = profileRepository
                .findByUserId(id)
                .orElseThrow(UserNotFoundException::new);

        return ProfileMapper.INSTANCE.toDto(profile);
    }

    @Cacheable(value = "evently_friends", key = "#id")
    public List<UserDto> getFriends(String id,
                                    int pageNumber,
                                    int pageSize) {
        final User user = userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new);

        return relationshipRepository
                .findByUser(user, PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(friend -> UserMapper.INSTANCE.toDto(friend.getFriend()))
                .toList();
    }

    public RegistrationDto startRegistration(StartRegistrationRequestDto request) {

        return null;
    }

    public void createUser(CreateUserRequestDto request) {

    }
}