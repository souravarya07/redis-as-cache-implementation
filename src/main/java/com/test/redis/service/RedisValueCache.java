package com.test.redis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisValueCache {

    private final ValueOperations<String, Object> valueOperations;


    public RedisValueCache(final RedisTemplate<String, Object> redisTemplate) {
        valueOperations = redisTemplate.opsForValue();
    }

    public void cache(final String key, final Object data) {
//        valueOperations.set(key, data);
        valueOperations.set(key, data, 5000, TimeUnit.MILLISECONDS);
    }

    public Object getCacheValue(final String key) {
        return valueOperations.get(key);
    }

    public void deleteValue(final String key) {
        valueOperations.getOperations().delete(key);
    }
}
