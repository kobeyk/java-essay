package com.appleyk.io.netty.inandoutboundhandler.client;

import com.appleyk.io.netty.inandoutboundhandler.codec.MyByteToLongDecoder;
import com.appleyk.io.netty.inandoutboundhandler.codec.MyLongToByteEncoder;
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
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  2:47 下午 2021/2/23
 */
public class MyClient {
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
                            // 添加一个入站的ByteToLongDecoder（收到消息了，要进行解码）
                            pipeline.addLast(new MyByteToLongDecoder());
                            // 添加一个出站的LongToByte的编码器Encoder（出去了，就是发送，肯定要编码）
                            pipeline.addLast(new MyLongToByteEncoder());
                            // 添加一个自定义的handler（来处理服务端发送来的消息）
                            pipeline.addLast(new MyClientHandler());
                        }
                    });
            ChannelFuture future = client.connect("localhost", 6666).sync();
            future.channel().closeFuture();
        }catch (Exception e){
            worker.shutdownGracefully();
        }
    }
}
