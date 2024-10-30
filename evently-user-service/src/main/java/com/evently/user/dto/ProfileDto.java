package com.evently.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private String id;
    private String name;
    private String username;
    private String pronouns;
    private String description;
    private Date birthdate;
    private int friendsCount;
}
