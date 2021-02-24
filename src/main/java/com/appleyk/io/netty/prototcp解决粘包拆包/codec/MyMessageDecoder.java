package com.appleyk.io.netty.prototcp解决粘包拆包.codec;

import com.appleyk.io.netty.prototcp解决粘包拆包.MessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * <p>越努力，越幸运</p>
 * 对消息协议对象进行解码（利用长度，解决拆包问题）
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:31 下午 2021/2/24
 */
public class MyMessageDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf buf, List<Object> out) throws Exception {
        System.out.println("MyMessageDecoder#decode 方法被调用");
        // 这里需要将得到的二进制字节码转成我们的messageProtocol数据包(对象)
        int len = buf.readInt();
        // 通过长度获取对应字节数组
        byte[] content = new byte[len];
        // 将缓冲中的数据读取到字节数组中（注意，这是读取一定长度的，所以不会拆包）
        buf.readBytes(content);
        // new消息协议对象并放入到list中传给下一个handler
        out.add(new MessageProtocol(content));
    }
}
