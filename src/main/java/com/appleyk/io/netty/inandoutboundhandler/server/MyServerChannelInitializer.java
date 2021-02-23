package com.appleyk.io.netty.inandoutboundhandler.server;

import com.appleyk.io.netty.inandoutboundhandler.codec.MyByteToLongDecoder;
import com.appleyk.io.netty.inandoutboundhandler.codec.MyLongToByteEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * <p>越努力，越幸运</p>
 * 通道初始化器
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  2:44 下午 2021/2/23
 */
public class MyServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        // 先添加入站ByteToLong的解码器
        pipeline.addLast(new MyByteToLongDecoder());
        // 再添加出站LongToByte的编码器
        pipeline.addLast(new MyLongToByteEncoder());
        // 再添加我们自定义的handler
        pipeline.addLast(new MyServerHandler());
    }
}
