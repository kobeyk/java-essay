package com.appleyk.io.netty.inandoutboundhandler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * <p>越努力，越幸运</p>
 * Long类型转ByteBuf（编码器）
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  3:02 下午 2021/2/23
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

    /**
     * 编码
     * @param ctx 通道处理器上下文
     * @param msg 消息
     * @param buf netty特有的封装过的ByteBuf类型（编码后的数据）
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf buf) throws Exception {
        System.out.println("===== 【消息进行编码】 MyLongToByteEncoder#encode被调用了 =====");
        // 往缓冲区中写数据
        buf.writeLong(msg);
    }
}
