package com.appleyk.io.netty.codec.server;

import com.appleyk.io.netty.codec.protobuf.model.StudentModel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  2:34 下午 2021/2/22
 */
public class MyPbHandler extends SimpleChannelInboundHandler<StudentModel.Student> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, StudentModel.Student student) throws Exception {
        System.out.println("======服务端收到客户端发送来的消息如下：");
        System.out.println("学生ID："+student.getId()+",学生姓名："+student.getName());
    }
}
