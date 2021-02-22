package com.appleyk.io.netty.codec.client;

import com.appleyk.io.netty.codec.protobuf.model.StudentModel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  2:01 下午 2021/2/22
 */
public class MyNettyClientHandler extends ChannelInboundHandlerAdapter {

    // 当通道处于就绪状态时就会触发下面的方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

          // 这里有个问题，发送的pb数据类型会有很多种，如何统一发送？如何让服务端统计接收？

          // 发送一个pb student对象 给服务端
        StudentModel.Student student = StudentModel.Student.newBuilder()
                .setId(10001)
                .setName("宋江2222").build();
        ctx.writeAndFlush(student);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端收到消息："+msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常："+cause.getMessage());
    }
}
