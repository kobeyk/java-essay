package com.appleyk.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class DiscardServer {
    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run(){
        // boos线程,接收连进来的请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        // worker线程，用来处理已经被接收的连接，一旦boss接收到连接，就会把注册信息注册到worker上
        EventLoopGroup workerGroup  = new NioEventLoopGroup();
        try{
            // NIO启动辅助类
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    // 使用NioServerSocketChannel类监听新连进来的Channel
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {//配置新的处理类
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                     //BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，
                    // 用于临时存放已完成三次握手的请求的队列的最大长度。如果未设置或所设置的值小于1，Java将使用默认值50。
                    .option(ChannelOption.SO_BACKLOG,128)
                    // 是否启用心跳保活机制。在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）
                    // 并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活。
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            // Netty所有的I/O操作都是异步的。因为一个操作可能不会立即返回，
            // 所以我们需要一种用于在之后得某个时间点确定其结果的方法。
            // 为此，Netty提供了ChannelFuture接口，其addListener()方法注册了一个ChannelFutureListener，
            // 以便在某个操作完成时（无论是否成功）得到通知。
            // 绑定端口，开始接收进来的连接
            ChannelFuture future = bootstrap.bind(this.port).sync();
            System.out.println("Netty Server start .....");
            future.channel().closeFuture().sync();

        }catch (Exception e){

        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {

        new DiscardServer(8080).run();

    }
}
