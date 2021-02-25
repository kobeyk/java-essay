package com.appleyk.io.netty.inandoutboundhandler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * <p>越努力，越幸运</p>
 * 自定义一个ByteBuf转Long的解码器
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  2:44 下午 2021/2/23
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /**
     * 解码实现
     * 会根据接收的数据，被调用多次，直到确定没有新的元素被添加到list中
     * 或者是ByteBuf没有更多的可读字节位置
     * 如果list不为空，就会将list中的内容传递给下一个ChannelInboundHandler处理
     * 该处理器的方法也会被调用多次
     * @param ctx 通道处理器上下文
     * @param byteBuf 入站的ByteBuf
     * @param list 将解码后的数据放到一个list集合中，这个list会传给下一个handler进行处理
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("===== 【消息进行解码】MyByteToLongDecoder#decode方法被调用了 ===== ");
        if (byteBuf.readableBytes()>=8){
            list.add(byteBuf.readLong());
        }
    }
}
