package com.appleyk.io.netty.sample案例;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>越努力，越幸运</p>
 * 服务端
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  1:46 下午 2021/2/8
 */
public class NettyServer {
    public static void main(String[] args) throws InterruptedException {
        // 创建BossGroup 和 WorkerGroup
        /**
         * 说明：
         * 1、创建了两个线程组
         * 2、bossGroup： 只处理accept请求，真正的客户端业务处理，会交给workerGroup完成
         * 3、两个都是无限循环
         * 4、bossGroup 和 workerGroup含有的子线程（NioEventLoop）的个数
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
        // 创建服务器端启动对象
        ServerBootstrap bootstrap = new ServerBootstrap();

        // 使用链式配置启动参数(builder模式)
        bootstrap.group(bossGroup,workerGroup) // 设置两个线程组
            .channel(NioServerSocketChannel.class) // 服务端使用NioServerSocketChannel(通道)实现
            .option(ChannelOption.SO_BACKLOG,128) // 设置线程队列等待连接的个数
            .childOption(ChannelOption.SO_KEEPALIVE,true) // 设置子channel的连接状态始终为保持连接
            .childHandler(new ChannelInitializer<SocketChannel>() { // 创建一个通道的初始化对象（匿名对象）

                // 给pipeline设置处理器
                @Override
                protected void initChannel(SocketChannel sc) throws Exception {
                    // 可以使用一个集合管理SocketChannel，再推送消息时
                    // 将业务加入到各个channel对应的EventLoop的taskQueue和scheduleTaskQueue中
                    System.out.println("客户端channel hashCode = "+sc.hashCode());
                    // 拿到客户端channel（连接进来的）的管道
                    // 可以通过channel拿到pipeline，反之也可以
                    ChannelPipeline pipeline = sc.pipeline();
                    // 将我们自定义的handler加进管道里
                    pipeline.addLast(new NettyServerHandler());
                }
            }) ; // 给我们的workerGroup 的 EventLoop设置业务处理器（可以是netty的也可以是我们自定义的处理器）
        System.out.println("服务器已启动...");
        // 绑定端口,并同步处理，拿到channelFuture对象
        ChannelFuture channelFuture = bootstrap.bind(6666).sync();
        // 对关闭通道进行监听(有消息关闭时，才会关闭)
        channelFuture.channel().closeFuture().sync();
        }finally {
            // 优雅的关闭netty
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
