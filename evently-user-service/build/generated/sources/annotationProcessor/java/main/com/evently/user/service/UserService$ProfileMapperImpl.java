package com.evently.user.service;

import com.evently.user.dto.ProfileDto;
import com.evently.user.entity.Profile;
import com.evently.user.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-29T18:34:10+0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 19.0.2 (Amazon.com Inc.)"
)
@Component
class UserService$ProfileMapperImpl implements UserService.ProfileMapper {

    @Override
    public ProfileDto toDto(Profile profile) {
        if ( profile == null ) {
            return null;
        }

        ProfileDto.ProfileDtoBuilder profileDto = ProfileDto.builder();

        profileDto.username( profileUserUsername( profile ) );
        profileDto.id( profile.getId() );
        profileDto.pronouns( profile.getPronouns() );
        profileDto.description( profile.getDescription() );
        profileDto.birthdate( profile.getBirthdate() );
        profileDto.friendsCount( profile.getFriendsCount() );

        return profileDto.build();
    }

    private String profileUserUsername(Profile profile) {
        if ( profile == null ) {
            return null;
        }
        User user = profile.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }
}
