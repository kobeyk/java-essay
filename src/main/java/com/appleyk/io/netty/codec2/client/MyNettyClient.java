package com.appleyk.io.netty.codec2.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  3:51 下午 2021/2/22
 */
public class MyNettyClient {
    public static void main(String[] args) {
        EventLoopGroup worker = new NioEventLoopGroup();
        try{
            Bootstrap client = new Bootstrap();
            client.group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();
                            pipeline.addLast(new ProtobufEncoder());
                            pipeline.addLast(new StringDecoder());
                            pipeline.addLast(new MyNettyClientHandler());
                        }
                    });
            ChannelFuture future = client.connect("localhost", 6666).sync();
            System.out.println("客户端已连接....");
            future.channel().closeFuture();
        }catch (Exception e){
            worker.shutdownGracefully();
        }
    }
}
