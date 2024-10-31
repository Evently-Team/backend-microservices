package com.evently.user.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("cache.ttl")
public class CacheTtlConfiguration {

    private long registration;

    private long userCache;

    private long profileCache;

    private long friendsCache;
}
