package com.netty.rpc.provider;


import com.netty.rpc.common.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String say(String content) {
        return "你好，我想对你说："+content;
    }
}
