package com.appleyk.netty.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //丢弃所有请求过来的数据，一律忽视
        //((ByteBuf)msg).release();

        //ByteBuf in = (ByteBuf)msg;
        //try{
        //    while(in.isReadable()){
        //        System.out.println((char) in.readByte());
        //        System.out.flush();
        //    }
        //}finally {
        //    // 手动释放下byteBuf（减少buf计数器引用，减为0时，会被回收）
        //    ReferenceCountUtil.release(msg);
        //}

        // 往客户端写回应消息，客户端发的什么，原封不动写回去
        ctx.write(msg);
        ctx.flush();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 一个请求就是一个Channel，即连接
        System.out.println(cause.getMessage());
        // 当发现异常时，关闭连接
        ctx.close();
    }
}
