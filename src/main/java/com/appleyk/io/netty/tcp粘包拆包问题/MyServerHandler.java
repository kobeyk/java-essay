package com.appleyk.io.netty.tcp粘包拆包问题;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.UUID;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:40 下午 2021/2/24
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {
        byte[] buffer = new byte[byteBuf.readableBytes()];
        // 从缓冲区读取数据到字节数组
        byteBuf.readBytes(buffer);
        System.out.println("服务端收到消息："+new String(buffer, CharsetUtil.UTF_8));
        System.out.println("服务器接收到的消息量 = "+(++count));

        // 回一些数据给客户端,回一些随机id
        ByteBuf buf = Unpooled.copiedBuffer(UUID.randomUUID().toString()+" ", CharsetUtil.UTF_8);
        ctx.writeAndFlush(buf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }
}
