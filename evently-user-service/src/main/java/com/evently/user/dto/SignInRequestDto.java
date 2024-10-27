package com.evently.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInRequestDto {

    @NotBlank(message = "Username or email cannot be empty")
    @NotNull(message = "Username or email cannot be null")
    @Schema(description = "Username or email",
            example = "test")
    private String usernameOrEmail;

    @NotBlank(message = "Password cannot be blank")
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Size(max = 64, message = "Password cannot be longer than 64 characters")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one number and " +
                    "one special character")
    @Schema(description = "Password",
            example = "Qwerty12345!!")
    private String password;
}
