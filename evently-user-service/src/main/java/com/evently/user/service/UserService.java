package com.evently.user.service;

import com.evently.user.dao.cache.RegistrationRepository;
import com.evently.user.dao.persistent.CredentialRepository;
import com.evently.user.dao.persistent.ProfileRepository;
import com.evently.user.dao.persistent.RelationshipRepository;
import com.evently.user.datasource.SlaveTransactional;
import com.evently.user.dto.*;
import com.evently.user.entity.cache.Registration;
import com.evently.user.entity.persistent.Credentials;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final RelationshipRepository relationshipRepository;
    private final CredentialRepository credentialRepository;
    private final RegistrationRepository registrationRepository;

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

    @SlaveTransactional
    @Cacheable(value = "user", key = "#id")
    public UserDto getUserById(UUID id) {
        return UserMapper.INSTANCE.toDto(userRepository
                .findById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    @SlaveTransactional
    @Cacheable(value = "profile", key = "#id")
    public ProfileDto getUserProfileById(UUID id) {
        final Profile profile = profileRepository
                .findByUserId(id)
                .orElseThrow(UserNotFoundException::new);

        return ProfileMapper.INSTANCE.toDto(profile);
    }

    @SlaveTransactional
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

    @Transactional
    public RegistrationDto startRegistration(StartRegistrationRequestDto request) {
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        final String passwordFingerprint = bCryptPasswordEncoder.encode(request.getPassword());

        final String verificationCode = String.format("%06d", (int)(Math.random() * 1000000));
        final Registration registration = Registration.builder()
                .name(request.getName())
                .email(request.getEmail())
                .username(request.getUsername())
                .verificationCode(verificationCode)
                .passwordFingerprint(passwordFingerprint)
                .build();
        registrationRepository.save(registration);

        return RegistrationDto.builder()
                .registrationId(registration.getId())
                .build();
    }

    @Transactional
    public UserDto createUser(CreateUserRequestDto request) {
        final Registration registration = registrationRepository
                .findById(request.getRegistrationId())
                .orElseThrow(UserNotFoundException::new);

        final User user = User.builder()
                .username(registration.getUsername())
                .name(registration.getName())
                .build();
        userRepository.save(user);

        final Credentials credentials = Credentials.builder()
                .passwordFingerprint(registration.getPasswordFingerprint())
                .user(user)
                .build();
        credentialRepository.save(credentials);

        final Profile profile = Profile.builder()
                .user(user)
                .build();
        profileRepository.save(profile);

        registrationRepository.delete(registration);

        return UserMapper.INSTANCE.toDto(user);
    }
}