package com.appleyk.io.netty.sample案例;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  2:24 下午 2021/2/8
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    // 当通道就绪就会发触发该方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端 ctx："+ctx);
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,我是客户端，汪汪汪", CharsetUtil.UTF_8));
    }

    // 当通道有读取事件时，会触发
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 转
        ByteBuf buf = (ByteBuf)msg;
        // 按UTF-8进行转换
        System.out.println("服务端回复："+buf.toString(CharsetUtil.UTF_8));
        System.out.println("服务端地址："+ctx.channel().remoteAddress());
    }

    // 异常发生时，关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(""+cause.getMessage());
        ctx.close();
    }
}
