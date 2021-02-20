package com.appleyk.io.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  8:27 下午 2021/2/19
 */
public class MyServer {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            ChannelPipeline pipeline = sc.pipeline();
                            /**
                             * http协议是无状态的，浏览器和服务器间的请求响应一次，下一次会重新创建新的连接
                             * 要求：实现基于websocket的长连接的全双工的交互
                             * 使用http的编解码处理器
                             */
                            pipeline.addLast(new HttpServerCodec());
                            // http协议分块传输编码技术
                            pipeline.addLast(new ChunkedWriteHandler());
                            /**
                             * 说明：
                             * 1、http协议数据传输过程是分段的，HttpObjectAggregator可以将多个段聚合起来
                             * 2、当浏览器在传输大数据量的时候，就会发出多次http请求
                             */
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            /**
                             * 说明：
                             * WebSocket是双向的，在客户端-服务器通信的场景中使用的全双工协议，
                             * 与HTTP不同，它以ws://或wss://开头。它是一个有状态协议，这意味着客户端和服务器之间的连接将保持活动状态，
                             * 直到被任何一方（客户端或服务器）终止。在通过客户端和服务器中的任何一方关闭连接之后，连接将从两端终止。
                             *
                             * WebSocket 使用了自定义的二进制分帧格式，把每个应用消息切分成一或多个帧，发送到目的地之后再组装起来，
                             * 等到接收到完整的消息后再通知接收端
                             * 1、对于websocket而言，它的数据是以帧（frame）的形式传递的
                             * 2、WebsocketFrame抽象类有6个子类
                             * 3、浏览器请求时：ws://localhost:6666/hello 表示请求的uri
                             * 4、WebSocketServerProtocolHandler 核心功能将一个http协议升级为ws协议，并保持长连接
                             * 5、http升级为websocket是通过一个状态码来切换的 ： 101
                             * WebSocket默认一帧数据的最大长度（maxFramePayloadLength）是：65536，我们可以通过构造器改变这一值
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello",null,false,65536));
                            // 添加自定义的handler，处理http发过来的数据
                            pipeline.addLast(new MyTextWebsocketFrameHandler());
                        }
                    });
            System.out.println("服务端已启动.....");
            ChannelFuture channelFuture = server.bind(8088).sync();
            channelFuture.channel().closeFuture();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
