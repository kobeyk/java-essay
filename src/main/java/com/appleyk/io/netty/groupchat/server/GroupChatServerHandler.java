package com.appleyk.io.netty.groupchat.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>越努力，越幸运</p>
 * 群聊服务端处理器
 * 如何实现一个私聊（点对点）的聊天系统?
 * 答：很简单，将用户id和通道进行绑定，选择给谁私聊时，只需要获取到对方的通道即可
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:09 上午 2021/2/14
 */
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {


    // 集中管理客户端通道
    private static ChannelGroup channelGroup = new DefaultChannelGroup("客户端通道组", GlobalEventExecutor.INSTANCE);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    // 异常处理
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 打印通道异常消息，顺便关闭客户端通道
        System.out.println(cause.getMessage());
        ctx.close();
    }

    // 处理客户端channel推送过来的消息（群发）
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        Channel channel = channelHandlerContext.channel();
        // 这个要从客户端通道中取出的数据msg转发给其他客户端，并且要排除掉自己
        // 首先服务端要把客户端发过来的数据打印出来
        System.out.println("[客户端]-"+channel.remoteAddress()+","+dateFormat.format(new Date())+"说："+msg);
        channelGroup.forEach(ch->{
            if(ch != channel){
                ch.writeAndFlush("[客户端]-"+channel.remoteAddress()+","+dateFormat.format(new Date())+"说："+msg);
            }else {
                ch.writeAndFlush("[我],"+dateFormat.format(new Date())+"-说："+msg);
            }
        });
    }

    // 客户端加入（通知其他客户端XXX加入群聊）
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 1、获取加入进来的客户端通道对象
        Channel channel = ctx.channel();
        // 2、向其他客户端广播消息
        channelGroup.writeAndFlush("[客户端]-"+channel.remoteAddress()+"加入群聊,"+dateFormat.format(new Date()));
        // 3、将当前的通道加入到组中（由于加入是在广播之前，所以当前的客户端通道并不会接收到加入群聊的通知）
        channelGroup.add(channel);
        // 4、当前在线人数
        System.out.printf("当前聊天室在线人数: %d\n",channelGroup.size());
    }

    // 客户端退出（通知其他客户端XXX退出群聊）
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        // 1、客户端退出后，会自动从当前的channelGroup集合中移除
        // 2、输出当前在线人数
        System.out.printf("当前聊天室在线人数: %d\n",channelGroup.size());
        // 3、通知其他客户端
        channelGroup.writeAndFlush("[客户端]-"+ctx.channel().remoteAddress()+"退出群聊，"+dateFormat.format(new Date()));
    }

    // 客户端准备就绪（服务端打印XXX已上线）
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[客户端]-"+ctx.channel().remoteAddress()+"已上线！");
    }

    // 客户端处于非活动状态（服务端打印XXX已下线）
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[客户端]-"+ctx.channel().remoteAddress()+"已下线！");
    }

}
