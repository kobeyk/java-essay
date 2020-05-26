package com.appleyk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>测试Controller</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/17 1:36 PM
 */
@RestController
@RequestMapping("/api")
public class THelloController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/hello")
    public String hello() {
        redisTemplate.opsForValue().set("name","appleyk");
        return "Hello,SpringBoot , " +redisTemplate.opsForValue().get("name");
    }

}
