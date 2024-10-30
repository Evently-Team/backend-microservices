package com.evently.user.service;

import com.evently.user.dao.persistent.ProfileRepository;
import com.evently.user.dao.persistent.RelationshipRepository;
import com.evently.user.dto.*;
import com.evently.user.entity.persistent.Profile;
import com.evently.user.entity.persistent.User;
import com.evently.user.dao.persistent.UserRepository;
import com.evently.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    @Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    interface ProfileMapper {

        ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);

        @Mapping(source = "user.username", target = "username")
        ProfileDto toDto(Profile profile);
    }

    @Cacheable(value = "user", key = "#id")
    public UserDto getUserById(UUID id) {
        return UserMapper.INSTANCE.toDto(userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    @Cacheable(value = "profile", key = "#id")
    public ProfileDto getUserProfileById(UUID id) {
        final Profile profile = profileRepository
                .findByUserId(id)
                .orElseThrow(UserNotFoundException::new);

        return ProfileMapper.INSTANCE.toDto(profile);
    }

    @Cacheable(value = "friends", key = "#id")
    public List<UserDto> getFriends(UUID id,
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