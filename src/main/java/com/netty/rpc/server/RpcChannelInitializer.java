package com.netty.rpc.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * <p>服务端通道初始化器，主要像通道管道里添加一些处理器</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 12:49 2021/3/1
 */
public class RpcChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        // 解码
        pipeline.addLast(new StringDecoder());
        // 编码
        pipeline.addLast(new StringEncoder());
        // 客户端channel处理器
        pipeline.addLast(new RpcServerHandler());
    }
}
