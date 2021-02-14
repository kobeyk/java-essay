package com.appleyk.io.netty.groupchat.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

/**
 * <p>越努力，越幸运</p>
 * 基于netty的群聊客户端
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:27 上午 2021/2/14
 */
public class GroupChatClient {

    private String host;
    private int port;

    public GroupChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void conn() throws Exception{
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            Bootstrap client = new Bootstrap();
            client.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();
                            pipeline.addLast("decoder",new StringDecoder());
                            pipeline.addLast("encoder",new StringEncoder());
                            pipeline.addLast(new GroupChatClientHandler());
                        }
                    });
            ChannelFuture channelFuture = client.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            // 拿到客户端channel后，就写一个循环发送消息
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()){
                String msg = scanner.nextLine();
                channel.writeAndFlush(msg);
            }
            channelFuture.channel().closeFuture().sync();
        }finally {
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new GroupChatClient("localhost",6666).conn();
    }

}
