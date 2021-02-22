package com.appleyk.io.netty.codec.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;

import java.util.Scanner;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  1:53 下午 2021/2/22
 */
public class MyNettyClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap client = new Bootstrap();
            client.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();
                            // 在当前pipeline管道中加入pb数据的编码器（对客户端发送给服务端的pb数据进行编码）
                            pipeline.addLast("pbEncoder",new ProtobufEncoder());
                            // 加入我们自定义的handler（注意pipeline中的handler是链式操作，所以pb处理器必须放上上面）
                            pipeline.addLast(new MyNettyClientHandler());

                        }
                    });
            ChannelFuture future = client.connect("localhost", 6666).sync();
            Channel channel = future.channel();
            boolean go = true;
            Scanner scanner = new Scanner(System.in);
            while (go) {
                System.out.println("等待用户输入（输入exit退出）：");
                String msg = scanner.nextLine();
                switch (msg) {
                    case "exit":
                        go = false;
                        break;
                    default:
                        channel.writeAndFlush(msg);
                        break;
                }
            }
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            workerGroup.shutdownGracefully();
        }
    }
}
