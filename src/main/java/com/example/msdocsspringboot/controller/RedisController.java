package com.example.msdocsspringboot.controller;

import com.example.msdocsspringboot.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("redis")
public class RedisController {

    private static final Logger log = LoggerFactory.getLogger(RedisController.class);


    @Autowired
    private RedisService redisService;

    @GetMapping("save")
    public Boolean save(String key, String value) {
        redisService.set(key,value);
        log.info("redis save key:{},value:{}",key,value);
        return Boolean.TRUE;
    }

    @GetMapping("query")
    public String query(String key) {
        Object object = redisService.get(key);
        log.info("redis get key:{},value:{}",key,object);
        return object.toString();
    }
}

