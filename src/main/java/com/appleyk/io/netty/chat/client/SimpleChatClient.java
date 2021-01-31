package com.appleyk.io.netty.chat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class SimpleChatClient {

    private String host;
    private int port;

    public SimpleChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new SimpleChatClientInitializer());
            // 连接netty服务端，获取一个通道
            Channel channel = bootstrap.connect(this.host, this.port).sync().channel();
            // 读用户输入的数据
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            // 利用通道来写消息
            while (true) {
                channel.writeAndFlush(in.readLine() + "\r\n");
            }
        } finally {
            // 优雅的关闭当前线程
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new SimpleChatClient("localhost",8080).run();
    }
}
