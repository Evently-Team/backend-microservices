package com.evently.user.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("jwt")
public class JwtTokenConfiguration {

    /**
     * Issuer of the JWT token.
     */
    private String issuer;

    /**
     * Key used to sign and verify JWT tokens.
     */
    private String secret;

    /**
     * Access token lifetime in seconds.
     */
    private long ttlInSeconds;
}
