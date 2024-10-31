package com.evently.user.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDto {

    @NotNull(message = "Email verification code cannot be null")
    private String verificationCode;

    @NotBlank(message = "Registration ID cannot be empty")
    private UUID registrationId;
}