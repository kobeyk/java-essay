package com.appleyk.io.netty.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  3:45 下午 2021/2/14
 */
public class MyServer {

    public static void main(String[] args) throws Exception{

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))// 日志处理器，netty自带的
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();
                            /**
                             * 加入一个netty提供的IdleStateHandler
                             * 说明：IdleStateHandler 是 netty提供的空闲状态的处理器
                             * 参数1：long readerIdleTime：表示多少时间没有读，就会发送一个心跳检测包检测是否连接
                             * 参数1：long writerIdleTime：表示多少时间没有写，就会发送一个心跳检测包检测是否连接
                             * 参数1：long allIdleTime：表示多少时间既没有读也没有写，就会发送一个心跳检测包检测是否连接
                             *
                             * JavaDoc摘抄：
                             * Triggers an {@link IdleStateEvent} when a {@link Channel} has not performed
                             * read, write, or both operation for a while.
                             * 当不在执行读、写或两者都发生一会时，会触发一个IdleStateEvent的事件
                             *
                             * 当IdleStateEvent 触发后，就会传递给管道的下一个handler去处理
                             * 通过调用（触发）下一个handler的userEventTrigger，在该方法中去处理IdleStateEvent（读空闲
                             * 、写空闲、读写均空闲）
                             * （因为往往客户端与服务端断开连接是很突然的，比如突然断电等，因此，需要主动用心跳来检测客户端是否真正的下线了）
                             */
                            pipeline.addLast(new IdleStateHandler(3,5,6, TimeUnit.SECONDS));
                            // 加入一个对空闲检测进一步处理的handler（自定义）
                            pipeline.addLast(new MyServerHandler());
                        }
                    });
            System.out.println("服务器已启动.....");
            ChannelFuture channelFuture = server.bind(6666).sync();
            channelFuture.channel().closeFuture().sync();

        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
