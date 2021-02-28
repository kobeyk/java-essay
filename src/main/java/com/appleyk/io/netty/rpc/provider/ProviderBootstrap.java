package com.appleyk.io.netty.rpc.provider;

import com.appleyk.io.netty.rpc.netty.server.RpcServer;

/**
 * <p>越努力，越幸运</p>
 * RPC服务端启动类
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  6:08 下午 2021/2/28
 */
public class ProviderBootstrap {

    private String host;
    private int port;

    public static void start(String host,int port){
        RpcServer.startServer(host,port);
    }

    public static void main(String[] args) {
        ProviderBootstrap.start("localhost",6666);
    }

}
