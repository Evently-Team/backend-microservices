package com.evently.user.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
@EnableRedisRepositories(basePackages = "com.evently.user.dao.cache")
@RequiredArgsConstructor
public class RedisConfiguration {

    private final CacheConfiguration cacheConfiguration;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        final HashMap<String, Long> ttls = new HashMap<>() {
            {
                put("registration", cacheConfiguration.getRegistrationTtlInSeconds());
                put("user", cacheConfiguration.getUserCacheTtlInSeconds());
                put("profile", cacheConfiguration.getProfileCacheTtlInSeconds());
                put("friends", cacheConfiguration.getFriendsCacheTtlInSeconds());
            }
        };

        final Map<String, RedisCacheConfiguration> redisCacheConfigurations = new HashMap<>();

        for (Map.Entry<String, Long> entry : ttls.entrySet()) {
            redisCacheConfigurations.put(entry.getKey(), RedisCacheConfiguration
                    .defaultCacheConfig()
                    .disableCachingNullValues()
                    .serializeValuesWith(RedisSerializationContext
                            .SerializationPair
                            .fromSerializer(new GenericJackson2JsonRedisSerializer()))
                    .entryTtl(Duration.ofSeconds(entry.getValue())));
        }

        return RedisCacheManager
                .builder(RedisCacheWriter
                        .nonLockingRedisCacheWriter(redisConnectionFactory))
                .withInitialCacheConfigurations(redisCacheConfigurations)
                .build();
    }
}
