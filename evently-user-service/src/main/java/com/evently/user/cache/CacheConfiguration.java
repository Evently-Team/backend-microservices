package com.evently.user.cache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("cache")
public class CacheConfiguration {

    private long registrationTtlInSeconds;

    private long userCacheTtlInSeconds;

    private long profileCacheTtlInSeconds;

    private long friendsCacheTtlInSeconds;
}
