package com.appleyk.io.netty.sample案例;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * <p>越努力，越幸运</p>
 * 客户端
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  2:16 下午 2021/2/8
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {

        // 客户端只需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            // 创建客户端启动对象
            Bootstrap bootstrap = new Bootstrap();
            // 设置相关参数
            bootstrap.group(group)//客户端也是一个循环处理,设置线程组
                    .channel(NioSocketChannel.class) // 客户端用这个通道来实现(反射)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            // 客户端管道
                            ChannelPipeline pipeline = sc.pipeline();
                            // 加入我们自定义的处理器
                            pipeline.addLast(new NettyClientHandler());
                        }
                    }); // 客户端也需要一个handler来处理
            System.out.println("客户端 is ok...");
            // 启动客户端并连接服务器(channelFuture异步机制，不会阻塞在这)
            ChannelFuture channelFuture = bootstrap.connect("localhost", 6666).sync();
            // 给关闭客户端端通道进行监听（并不是马上关闭，而是有关闭事件了才检测到并进行关闭）
            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
