package com.evently.user.dto;

import com.evently.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {

    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    private Role role;
    private String city;
    private String profileDescription;
    private List<String> profileLinks;
}
