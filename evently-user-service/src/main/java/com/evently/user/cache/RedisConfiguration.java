package com.evently.user.cache;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfiguration {

    @Bean
    public CacheManager cacheManager(final RedisConnectionFactory connectionFactory) {
        final RedisCacheConfiguration config = RedisCacheConfiguration
            .defaultCacheConfig()
            .serializeKeysWith(
                    RedisSerializationContext
                            .SerializationPair
                            .fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(
                    RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(new GenericJackson2JsonRedisSerializer()));

        return RedisCacheManager.builder()
                .cacheDefaults(config)
                .build();
    }
}
