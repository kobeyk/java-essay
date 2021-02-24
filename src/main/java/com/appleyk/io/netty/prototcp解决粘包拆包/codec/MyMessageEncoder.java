package com.appleyk.io.netty.prototcp解决粘包拆包.codec;

import com.appleyk.io.netty.prototcp解决粘包拆包.MessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * <p>越努力，越幸运</p>
 * 对自定义的消息协议进行编码
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:28 下午 2021/2/24
 */
public class MyMessageEncoder extends MessageToByteEncoder<MessageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyMessageEncoder#encode 方法被调用");
        // 把长度写进去
        out.writeInt(msg.getLen());
        // 把内容写进去
        out.writeBytes(msg.getContent());
    }
}
