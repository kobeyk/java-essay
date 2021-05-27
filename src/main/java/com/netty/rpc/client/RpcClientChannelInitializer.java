package com.netty.rpc.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * <p>客户端通道初始化器，比如向通道管道链中添加一些列处理器</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 13:23 2021/3/1
 */
public class RpcClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final RpcClientHandler rpcClientHandler;

    public RpcClientChannelInitializer(RpcClientHandler rpcClientHandler) {
        this.rpcClientHandler = rpcClientHandler;
    }

    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        ChannelPipeline pipeline = sc.pipeline();
        pipeline.addLast(new StringEncoder());
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(rpcClientHandler);
    }

    public RpcClientHandler getRpcClientHandler() {
        return rpcClientHandler;
    }
}
