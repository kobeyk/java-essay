package com.appleyk.io.netty.tcp粘包拆包问题;

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
        pipeline.addLast(new MyClientHandler());
    }
}
