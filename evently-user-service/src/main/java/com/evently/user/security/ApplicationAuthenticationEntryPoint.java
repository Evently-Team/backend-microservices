package com.evently.user.security;

import com.evently.user.exception.AuthenticationFailedException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@NoArgsConstructor
public class ApplicationAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest request,
                         final HttpServletResponse response,
                         final AuthenticationException authException) {
        throw new AuthenticationFailedException("JWT token is not provided, expired or invalid");
    }
}
