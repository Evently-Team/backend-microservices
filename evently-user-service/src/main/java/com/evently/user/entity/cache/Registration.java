package com.evently.user.entity.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Registration {

    private String name;
    private String email;
    private String username;
    private String passwordFingerprint;
}
