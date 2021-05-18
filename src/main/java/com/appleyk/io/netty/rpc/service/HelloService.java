package com.appleyk.io.netty.rpc.service;

/**
 * <p>越努力，越幸运</p>
 * RPC`公共接口
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  6:05 下午 2021/2/28
 */
public interface HelloService {
    String PROTOCOL = "HelloService#say#";
    String say(String msg);
}
