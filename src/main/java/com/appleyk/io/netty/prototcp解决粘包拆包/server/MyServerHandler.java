package com.appleyk.io.netty.prototcp解决粘包拆包.server;

import com.appleyk.io.netty.prototcp解决粘包拆包.MessageProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.util.Random;
import java.util.UUID;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:40 下午 2021/2/24
 */
public class MyServerHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol buf) throws Exception {
        // 接收到数据并处理

        // 1、先拿到长度
        int len = buf.getLen();
        byte[] content = buf.getContent();
        System.out.println("服务器接收到消息如下：");
        System.out.println("长度："+len);
        System.out.println("内容："+new String(content,CharsetUtil.UTF_8));
        System.out.println("服务器接收到的消息次数："+(++count));
        System.out.println("================== ==================");

        // 回复消息
        String res = UUID.randomUUID().toString()+new Random().nextInt(100000);
        // 构建消息协议包
        MessageProtocol messageProtocol = new MessageProtocol(res.getBytes(CharsetUtil.UTF_8));
        // 往通道里写
        ctx.writeAndFlush(messageProtocol);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }
}
