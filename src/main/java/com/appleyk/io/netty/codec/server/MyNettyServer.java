package com.appleyk.io.netty.codec.server;

import com.appleyk.io.netty.codec.protobuf.model.StudentModel;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  1:39 下午 2021/2/22
 */
public class MyNettyServer {

    public static void main(String[] args) {
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
                            // 加入protobuf的解码器(构造器参数：指定对哪种对象进行解码)
                            pipeline.addLast("pbDecoder",new ProtobufDecoder(StudentModel.Student.getDefaultInstance()));
                            // 添加我们自己的handler
                            pipeline.addLast(new MyPbHandler());
                        }
                    });
            System.out.println("服务器已启动，端口6666.....");
            ChannelFuture future = server.bind(6666).sync();
            future.channel().closeFuture().sync();
        }catch (Exception e){
            // 优雅地关闭线程池
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
