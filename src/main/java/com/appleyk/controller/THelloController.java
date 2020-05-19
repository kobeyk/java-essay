package com.appleyk.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
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

    @GetMapping("/hello")
    public String hello(){
        return "Hello,SpringBoot";
    }

}
