package com.appleyk.io.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * <p>越努力，越幸运</p>
 * 初始化channel handler
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  5:43 下午 2021/2/9
 */
public class TestServerInitializer extends ChannelInitializer <SocketChannel> {
    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        // 向管道加入处理器

        // 得到管道
        ChannelPipeline pipeline = sc.pipeline();

        // 加入一个netty 提供的httpServerCodec code =》[coder - decode]  编解码器
        // 1. netty提供的处理http的编码`解码器
        pipeline.addLast("MyHttpServerCodec",new HttpServerCodec());
        pipeline.addLast("MyHttpServerHandler",new TestHttpServerHandler());
    }
}
