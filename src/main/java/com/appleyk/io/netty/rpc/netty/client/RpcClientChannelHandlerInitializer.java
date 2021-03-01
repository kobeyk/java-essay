package com.appleyk.io.netty.rpc.netty.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * <p>越努力，越幸运</p>
 * Rpc`客户端`Nettt通道处理器初始化器
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  6:41 下午 2021/2/28
 */
public class RpcClientChannelHandlerInitializer extends ChannelInitializer<SocketChannel> {

    private RpcClientChannelHandler clientChannelHandler;

    public RpcClientChannelHandlerInitializer(RpcClientChannelHandler clientChannelHandler) {
        this.clientChannelHandler = clientChannelHandler;
    }

    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        // 添加编码器
        pipeline.addLast(new StringEncoder());
        // 添加解码器
        pipeline.addLast(new StringDecoder());
        // 添加自定义的handler
        pipeline.addLast(clientChannelHandler);
    }


}
