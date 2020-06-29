package com.appleyk.netty.chat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class SimpleChatServerHandler extends SimpleChannelInboundHandler<String> {
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 每当从服务器接收到新的客户端连接时，会调用这个方法
        Channel inComing = ctx.channel();
        for (Channel channel : channels) {
            // 告知每个客户端，xxx进来了
            channel.writeAndFlush("服务端 -- 欢迎"+channel.remoteAddress()+" 加入到群聊\n");
        }
        // 把新连接进来的请求加入到channels列表中
        channels.add(inComing);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
       // 每当从服务端收到客户端断开时，会调用这个方法
        Channel inComing = ctx.channel();
        for (Channel channel : channels) {
            // 告知所有客户端，xxx离开了
            channel.writeAndFlush("服务端 -- 请我们欢送"+inComing.remoteAddress()+"离开\n");
        }
        channels.remove(inComing);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        // 每当从服务端读到客户端写入信息时，调用该方法
        Channel inComing = ctx.channel();
        for (Channel channel : channels) {
            // 给别人发的时候就是自己的ip
            if(inComing!=channel){
                channel.writeAndFlush("【"+inComing.remoteAddress()+"说】："+s+"\n");
            }else{
                // 给自己发的时候，显示的就是我
                channel.writeAndFlush("【我说】："+s+"\n");
            }
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当服务端监听到客户端活动时，调用该方法
        Channel inComing = ctx.channel();
        System.out.println("聊天室当前在线人数："+channels.size()+","+
                inComing.remoteAddress()+"：在线！");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 当服务端监听到客户端不活动时，调用该方法
        Channel inComing = ctx.channel();
        System.out.println("聊天室当前在线人数："+channels.size()+","+
                inComing.remoteAddress()+"：离线！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当服务端接收到客户端由于IO错误或处理器在处理事件时抛出异常时，调用这个
        Channel inComing = ctx.channel();
        System.out.println("聊天室："+inComing.remoteAddress()+"异常！");
        cause.printStackTrace();
        // 异常时，就关闭客户端请求
        ctx.close();
    }
}
