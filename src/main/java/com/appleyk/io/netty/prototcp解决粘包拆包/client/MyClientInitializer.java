package com.appleyk.io.netty.prototcp解决粘包拆包.client;

import com.appleyk.io.netty.prototcp解决粘包拆包.codec.MyMessageDecoder;
import com.appleyk.io.netty.prototcp解决粘包拆包.codec.MyMessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:35 下午 2021/2/24
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        // 获取pipeline
        ChannelPipeline pipeline = sc.pipeline();
        // 添加编码器
        pipeline.addLast(new MyMessageEncoder());
        // 添加解码器
        pipeline.addLast(new MyMessageDecoder());
        // 添加我们自定义的handler
        pipeline.addLast(new MyClientHandler());
    }
}
