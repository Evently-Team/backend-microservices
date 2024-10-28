package com.evently.user.dto;

import com.evently.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {

    private String id;
    private String name;
    private String username;
    private String city;
    private String pronouns;
    private String description;
    private Date birthdate;
    private int friendsCount;
}
