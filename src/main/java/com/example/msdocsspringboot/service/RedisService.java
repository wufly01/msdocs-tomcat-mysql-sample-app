package com.example.msdocsspringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 存储键值对
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    // 存储键值对并设置过期时间
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    // 获取值
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    // 删除键
    public Boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    // 判断键是否存在
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    // 设置过期时间
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    // 操作哈希表
    public void hset(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public Object hget(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    // 操作列表
    public Long lpush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    public Object rpop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    // 操作集合
    public Long sadd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    // 操作有序集合
    public Boolean zadd(String key, Object value, double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }
}
