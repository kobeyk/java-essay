package com.netty.rpc.common;

public interface HelloService {
    public static final String FLAG = "HelloService#say#";
    String say(String content);
}
