package com.appleyk.io.netty.prototcp解决粘包拆包.client;

import com.appleyk.io.netty.prototcp解决粘包拆包.MessageProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:36 下午 2021/2/24
 */
public class MyClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private int count;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol buf) throws Exception {
        int len = buf.getLen();
        byte[] content = buf.getContent();
        System.out.println("客户端收到消息如下：");
        System.out.println("消息长度："+len);
        System.out.println("消息内容："+new String(content,CharsetUtil.UTF_8));
        System.out.println("客户端收到消息次数："+(++count));
        System.out.println("=============== ===============");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 客户端for循环N次，给服务端发送5条数据 "今天心情不错，学习了Netty和SpringBoot"
        for (int i = 0; i < 5; i++) {
            String msg = "今天心情不错，学习了Netty和SpringBoot";
            // 创建消息协议对象
            MessageProtocol messageProtocol = new MessageProtocol(msg.getBytes(CharsetUtil.UTF_8));
            ctx.writeAndFlush(messageProtocol);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
        ctx.close();
    }
}
