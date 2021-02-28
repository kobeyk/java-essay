package com.appleyk.io.netty.rpc.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * <p>越努力，越幸运</p>
 * RPC`Netty服务端通道初始化器
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  6:16 下午 2021/2/28
 */
public class RpcChannelHandlerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        // 添加string的编码器
        pipeline.addLast(new StringEncoder());
        // 添加string的解码器
        pipeline.addLast(new StringDecoder());
        // 添加我们自定义的服务端channelHandler
        pipeline.addLast(new RpcChannelHandler());
    }
}
