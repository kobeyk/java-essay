package com.appleyk.io.netty.codec2.client;

import com.appleyk.io.netty.codec2.protobuf.model.PbData;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  3:55 下午 2021/2/22
 */
public class MyNettyClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端通道已就绪，随时可以读写数据!");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        // 1、先读服务端发送的通知消息（String类型的）
        Channel channel = ctx.channel();
        System.out.println("客户端收到服务端发送来的消息："+s);
        // 2、然后再给服务端发送protocol buffer数据
        int type = new Random().nextInt(2);
        // 先构造student对象
        PbData.School.Builder schoolBuilder = PbData.School.newBuilder();
        System.out.println("type："+type);
        if ( 0 == type){
            // 发送teacher类型的pb数据,设置pb对象的数据类型
            schoolBuilder.setDataType(PbData.School.DataType.TeacherType);
            // 构建teacher builder
            PbData.Teacher.Builder teacherBuilder = PbData.Teacher.newBuilder();
            teacherBuilder.setId(System.currentTimeMillis());
            teacherBuilder.setName("Appleyk");
            schoolBuilder.setTeacher(teacherBuilder);
        }else {
            // 发送student类型的pb数据,设置pb对象的数据类型
            schoolBuilder.setDataType(PbData.School.DataType.StudentType);
            // 构建student builder
            PbData.Student.Builder studentBuilder = PbData.Student.newBuilder();
            studentBuilder.setId(System.currentTimeMillis());
            studentBuilder.setName("吴用");
            studentBuilder.setSex(new Random().nextInt(2)==0 ? "男":"女");
            studentBuilder.setAge(new Random().nextInt(50));
            schoolBuilder.setStudent(studentBuilder);
        }
        // 发送消息给服务端
        channel.writeAndFlush(schoolBuilder.build());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常："+cause.getMessage());
    }

}
