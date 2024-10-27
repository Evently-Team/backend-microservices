package com.evently.user.service;

import com.evently.user.entity.User;
import com.evently.user.dao.UserRepository;
import com.evently.user.dto.UserDto;
import com.evently.user.dto.UserProfileDto;
import com.evently.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    interface UserMapper {

        UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

        UserDto toDto(User user);

        UserProfileDto toProfileDto(User user);
    }

    /**
     * Fetches user data by ID.
     * @param  id user id
     * @return user data
     */
    public UserDto getUser(final int id) {
        log.info("Fetching user `{}`", id);

        final User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User `{}` wasn't found", id);

                    return new UserNotFoundException();
                });

        log.info("Successfully fetched user `{}`", id);

        return UserMapper.INSTANCE.toDto(user);
    }

    /**
     * Fetches user profile data by ID.
     * @param  id user id
     * @return user data
     */
    public UserProfileDto getUserProfile(final int id) {
        log.info("Fetching profile of user `{}`", id);

        final User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User `{}` wasn't found", id);

                    return new UserNotFoundException();
                });

        log.info("Successfully fetched profile of user `{}`", id);

        return UserMapper.INSTANCE.toProfileDto(user);
    }
}