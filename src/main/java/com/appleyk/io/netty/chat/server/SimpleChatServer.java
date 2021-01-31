package com.appleyk.io.netty.chat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class SimpleChatServer {

    private int port;

    public SimpleChatServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new SimpleChatServerInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            // 同步阻塞，直到绑定完返回一个future
            ChannelFuture f = bootstrap.bind(this.port).sync();
            System.out.println("聊天室启动了... ...");
            // 同步阻塞直到关闭future
            f.channel().closeFuture().sync();
        }finally {
            // 优雅的关闭线程池
            worker.shutdownGracefully();
            boss.shutdownGracefully();
            System.out.println("聊天室关闭了！");
            Thread.yield();
        }
    }

    public static void main(String[] args) throws Exception{
        new SimpleChatServer(8080).run();
    }
}
