package com.appleyk.io.netty.groupchat.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * <p>越努力，越幸运</p>
 * 基于netty的群聊服务端
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:00 上午 2021/2/14
 */
public class GroupChatServer {

    private int port;

    public GroupChatServer(int port) {
        this.port = port;
    }

    public void run() throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();
                            // string（字符串）解码器，有了它，发送字符串就不用我们再编解码了
                            pipeline.addLast("decoder",new StringDecoder());
                            pipeline.addLast("encoder",new StringEncoder());
                            // 添加我们自己的handler
                            pipeline.addLast(new GroupChatServerHandler());
                        }
                    });
            ChannelFuture channelFuture = server.bind(port).sync();
            channelFuture.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    System.out.println("服务器已启动...等待用户连接");
                }
            });
            // 监听关闭回调
            channelFuture.channel().closeFuture().sync();
        }finally {
            // 优雅的关闭线程池
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new GroupChatServer(6666).run();
    }
}
