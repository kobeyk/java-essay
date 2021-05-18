package com.netty.rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>服务端</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 12:45 2021/3/1
 */
public class RpcServer {

    public static void startServer(String host,int port){
        startServer0(host,port);
    }

    private static void startServer0(String host,int port){
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();
        try{
            ServerBootstrap server = new ServerBootstrap();
            server.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,108)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new RpcChannelInitializer());
            ChannelFuture future = server.bind(host, port).sync();
            System.out.println("服务端已启动，端口："+port);
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
