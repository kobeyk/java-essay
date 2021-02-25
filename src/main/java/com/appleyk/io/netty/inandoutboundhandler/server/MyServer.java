package com.appleyk.io.netty.inandoutboundhandler.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  2:41 下午 2021/2/23
 */
public class MyServer {
    public static void main(String[] args) {

        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();
        try{
            ServerBootstrap server = new ServerBootstrap();
            server.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerChannelInitializer());
            System.out.println("服务端已启动 。。。。");
            ChannelFuture future = server.bind(6666).sync();
            future.channel().closeFuture();
        }catch (Exception e){
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}
