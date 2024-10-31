package com.evently.user.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("cache.ttl")
public class CacheTtlConfiguration {

    /**
     * Lifetime of cache for registrations started (where email
     * verification codes are stored) in seconds.
     */
    private long registration;

    /**
     * Lifetime of cache for users data in seconds.
     */
    private long userCache;

    /**
     * Lifetime of cache for user profiles data seconds.
     */
    private long profileCache;

    /**
     * Lifetime of cache for user friends in seconds.
     */
    private long friendsCache;
}
