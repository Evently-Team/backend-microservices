package com.evently.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StartRegistrationRequestDto {

    @NotBlank(message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must be at least 2 characters long")
    @Size(max = 32, message = "Name must be at most 32 characters long")
    private String name;

    @NotBlank(message = "Username cannot be empty")
    @NotNull(message = "Username cannot be null")
    @Size(min = 3, message = "Username must be at least 3 characters long")
    @Size(max = 32, message = "Username must be at most 32 characters long")
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9]*$",
            message = "Username must start with an uppercase letter and continue with lowercase letters")
    private String username;

    @Email(message = "Email is not valid")
    @NotNull(message = "Email cannot be null")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Size(max = 64, message = "Password must be at most 64 characters long")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number and " +
                    "one special character")
    private String password;
}
