package com.evently.user.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("application.jwt-token")
public class JwtTokenConfiguration {

    /**
     * Issuer of the JWT token.
     */
    private String issuer;

    /**
     * Key used to sign and verify JWT tokens.
     */
    private String secretKey;

    /**
     * Name of the cookie used to store access token.
     */
    private String cookieName;

    /**
     * Access token lifetime in seconds.
     */
    private long lifetimeInSeconds;
}
