package com.appleyk.io.netty.codec2.server;

import com.appleyk.io.netty.codec2.protobuf.model.PbData;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  3:45 下午 2021/2/22
 */
public class MyNettyServerHandler extends SimpleChannelInboundHandler<PbData.School> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PbData.School school) throws Exception {
        Channel client = ctx.channel();
        System.out.println("========= 收到客户端"+client.remoteAddress()+"消息如下:");
        // 先拿到数据类型
        PbData.School.DataType dataType = school.getDataType();
        if (dataType.equals(PbData.School.DataType.StudentType)){
            PbData.Student student = school.getStudent();
            long id = student.getId();
            String name = student.getName();
            String sex = student.getSex();
            int age = student.getAge();
            System.out.printf("学生编号：%d,姓名：%s,性别：%s,年龄：%s\n",id,name,sex,age);
        }else if (dataType.equals(PbData.School.DataType.TeacherType)){
            PbData.Teacher teacher = school.getTeacher();
            long id = teacher.getId();
            String name = teacher.getName();
            System.out.printf("老师编号：%d,姓名：%s\n",id,name);
        }else {
            System.out.println("数据类型不支持，无法正常解析！！！");
            client.writeAndFlush("数据类型不支持，无法正常解析！！！");
        }
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        // 一旦有客户端连进来了，就给他发送一条消息，告诉他，我很好
        ctx.writeAndFlush("我很好，你呢，最近好吗，好久不见~~");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("读取数据异常了："+cause.getMessage());
    }
}
