package com.appleyk.io.netty.websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.time.LocalDate;

/**
 * <p>越努力，越幸运</p>
 * TextWebSocketFrame: 文本帧（frame）
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:06 下午 2021/2/19
 */
public class MyTextWebsocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        System.out.println("服务器收到消息："+msg.text());

        // 回复消息给浏览器
        Channel channel = ctx.channel();
        channel.writeAndFlush(new TextWebSocketFrame("服务器时间 - "+ LocalDate.now()+
                ",消息："+msg.text()));
    }

    // 客户端连接后就会触发
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // id表示一个唯一的一个值，一种是LongText(这个是唯一的)
        System.out.println("handlerAdded被调用了："+ctx.channel().id().asLongText());
        // 一种是ShortText(这个有可能重复)
        System.out.println("handlerAdded被调用了："+ctx.channel().id().asShortText());
    }

    // 客户端关闭后就会触发
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved被调用了："+ctx.channel().id().asLongText());
        System.out.println("客户端："+ctx.channel().remoteAddress()+",已下线！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("异常发生:"+cause.getMessage());
        ctx.close();
    }
}
