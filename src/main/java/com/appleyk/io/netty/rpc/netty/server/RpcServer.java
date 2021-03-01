package com.appleyk.io.netty.rpc.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>越努力，越幸运</p>
 * RPC`Netty服务端
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  6:11 下午 2021/2/28
 */
public class RpcServer {

    public static void startServer(String host,int port){
        startServer0(host,port);
    }

    /**
     * 启动netty服务
     * @param host 主机IP
     * @param port （tcp）服务端口
     */
    private static void startServer0(String host,int port){
        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup();
        try{
            ServerBootstrap server = new ServerBootstrap();
            server.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new RpcChannelHandlerInitializer());
            ChannelFuture future = server.bind(host, port).sync();
            System.out.println("=== Rpc服务端已经启动 ===");
            future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

}
