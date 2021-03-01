package com.appleyk.io.netty.rpc.provider;

import com.appleyk.io.netty.rpc.service.HelloService;

/**
 * <p>越努力，越幸运</p>
 * RPC`服务提供者，对公共接口进行具体的业务实现
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  6:06 下午 2021/2/28
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String say(String msg) {
        if (msg.trim().equals("")){
            return "你说的是什么，我不知道啊！";
        }
        return "你好，我想对你说："+msg;
    }
}
