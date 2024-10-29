package com.evently.user.service;

import com.evently.user.dto.UserDto;
import com.evently.user.entity.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-29T18:34:10+0500",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 19.0.2 (Amazon.com Inc.)"
)
class UserService$UserMapperImpl implements UserService.UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.id( user.getId() );
        userDto.name( user.getName() );
        userDto.username( user.getUsername() );

        return userDto.build();
    }
}
