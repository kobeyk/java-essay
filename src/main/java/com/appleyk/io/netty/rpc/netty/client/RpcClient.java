package com.appleyk.io.netty.rpc.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.lang.reflect.Proxy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>越努力，越幸运</p>
 * Rpc`客户端`服务消费者
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  6:37 下午 2021/2/28
 */
public class RpcClient {

    private ExecutorService executorService = Executors.newFixedThreadPool(20);
    private static RpcClientChannelHandler client;

    /**
     * 在这个服务消费端，进行代理，拿到Service的代理，然后处理函数，会向nettyServer发送消息，并异步接收响应
     */
    public Object getObject(Class<?> clz) {
        return Proxy.newProxyInstance(clz.getClassLoader(),
                new Class<?>[]{clz}, (proxy, method, args) -> {
                    if (proxy.getClass().equals(this.getClass())) {
                        return method.invoke(args);
                    }
                    System.out.println("=== invokeMethod 方法调用了");
                    if (client == null) {
                        initClient();
                    }
                    client.setParam(args[0].toString());
                    return executorService.submit(client).get();
                });
    }

    private static void initClient() {
        client = new RpcClientChannelHandler();
        EventLoopGroup group = new NioEventLoopGroup(1);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new RpcClientChannelHandlerInitializer(client));
            bootstrap.connect("localhost", 6666).sync();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
