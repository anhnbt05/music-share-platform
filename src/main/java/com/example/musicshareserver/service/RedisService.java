package com.example.musicshareserver.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisService {

    private StringRedisTemplate redisTemplate;

    @Value("${REDIS_HOST}")
    private String host;

    @Value("${redis.port:6379}")
    private int port;

    @Value("${REDIS_USER}")
    private String username;

    @Value("${REDIS_PASSWORD:}")
    private String password;

    @PostConstruct
    public void init() {
        RedisStandaloneConfiguration config =
                new RedisStandaloneConfiguration(host, port);

        if (!password.isBlank()) {
            config.setPassword(RedisPassword.of(password));
        }

        LettuceConnectionFactory factory = new LettuceConnectionFactory(config);
        factory.afterPropertiesSet();

        redisTemplate = new StringRedisTemplate(factory);
        System.out.println("Redis connected successfully!");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Redis connection closed");
    }

    public void setEx(String key, long ttlSeconds, String value) {
        redisTemplate.opsForValue()
                .set(key, value, Duration.ofSeconds(ttlSeconds));
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void del(String... keys) {
        redisTemplate.delete(java.util.List.of(keys));
    }
}
