package com.netty.rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p></p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 13:10 2021/3/1
 */
public class RpcClient {

    private Bootstrap client;
    private EventLoopGroup group;
    private ChannelFuture future;
    private final String host;
    private final int port;
    private RpcClientChannelInitializer channelInitializer;
    private final ExecutorService executors =  Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*2);

    public RpcClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Object getObject(Class<?> clz){
        return Proxy.newProxyInstance(clz.getClassLoader(),
                new Class<?>[]{clz},(proxy,method,args)->{
                    if (proxy.getClass().equals(this.getClass())){
                        return method.invoke(args);
                    }

                    //  只有在被代理对象执行方法时，才会初始化client
                    if(channelInitializer == null){
                        initClient();
                    }
                    RpcClientHandler clientHandler =channelInitializer.getRpcClientHandler();
                    clientHandler.setParam(args[0].toString());
                    return executors.submit(clientHandler).get();
                });
    }

    private void initClient(){
        if (channelInitializer == null){
            channelInitializer = new RpcClientChannelInitializer(new RpcClientHandler());
        }
        client = new Bootstrap();
        group = new NioEventLoopGroup(1);
        client.group(group)
                .channel(NioSocketChannel.class)
                .handler(channelInitializer);
        try{
            future = client.connect(host, port).sync();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
