package com.appleyk.io.netty.groupchat.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * <p>越努力，越幸运</p>
 * 群聊客户端通道（消息）处理器
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:31 上午 2021/2/14
 */
public class GroupChatClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg.trim());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
