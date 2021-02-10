package com.appleyk.io.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * <p>越努力，越幸运</p>
 * 1、SimpleChannelInboundHandler是ChannelInboundHandlerAdapter的子类，提供的方法更全一些
 * 2、HttpObject：客户端和服务器端相互通信的数据被封装成了 HttpObject类型
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  5:42 下午 2021/2/9
 */
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    // 通道就绪事件（活动状态）
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    // handler加入了会触发该方法
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    // 读取客户端数据
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.out.println("对应的channel = "+ctx.channel()+",获取管道pipeline = "+ctx.pipeline()
                +"通过pipeline获取其对应的channel（你中有我，我中有你） ="+ctx.pipeline().channel());
        System.out.println("获取ctx当前处理事件的处理器handler ="+ctx.handler());
        // 先判断下，msg是不是一个 httpRequest请求
        if (msg instanceof HttpRequest){
            System.out.println("ctx的真实类型：" +ctx.getClass());
            FullHttpResponse response ;
            // 强转成httpRequest
            HttpRequest request = (HttpRequest)msg;
            // 拿到uri
            URI uri = new URI(request.uri());
            // 过滤特定资源（用netty做http服务器的少之又少，这里只是为了了解一下，做http服务器的用spring）
            if(uri.getPath().equals("/favicon.ico")){
                System.out.println("请求了favicon.icon,直接返回");
                response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,Unpooled.copiedBuffer("",CharsetUtil.UTF_8));
                response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=utf-8");
                response.headers().set(HttpHeaderNames.CONTENT_LENGTH,0);
                response.setStatus(HttpResponseStatus.OK);
                ctx.writeAndFlush(response);
                return;
            }
            System.out.println("========= ==========");
            System.out.println("pipeline hashCode ="+ ctx.pipeline().hashCode());
            System.out.println("msg 类型 = "+msg.getClass());
            System.out.println("客户端地址 = "+ ctx.channel().remoteAddress());
            // 回复信息给浏览器[http协议]
            ByteBuf content = Unpooled.copiedBuffer("hello,我是服务器", CharsetUtil.UTF_8);
            // 构造一个http的响应及httpResponse
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,content);
            // 设置其他属性信息(一定不要忘了utf-8，防止浏览器中文乱码)
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain;charset=utf-8");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH,content.readableBytes());

            // 将构建好的response返回
            ctx.writeAndFlush(response);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
    }
}
