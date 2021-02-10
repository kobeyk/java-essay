package com.appleyk.io.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:46 下午 2021/2/10
 */
public class NettyByteBuf02 {
    public static void main(String[] args) {
          // 创建ByteBuf
        ByteBuf buf = Unpooled.copiedBuffer("hello,world!", CharsetUtil.UTF_8);
        // 使用相关的API
        if (buf.hasArray()){
            byte[] content = buf.array();
            // 将content转成字符串
            String msg = new String(content,CharsetUtil.UTF_8);
            System.out.println(msg);
            // byteBuf = UnpooledByteBufAllocator
            // $InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 0, widx: 13, cap: 27)
            // 一个中文utf-8占3个字节，空格和感叹号各站1个字节，在加上hello的5个，总过是widx=5+2+6=13
            System.out.println("byteBuf = "+buf);
            System.out.println("偏移量："+buf.arrayOffset());
            System.out.println("readerIndex："+buf.readerIndex());
            System.out.println("writerIndex："+buf.writerIndex());
            System.out.println("capacity："+buf.capacity());
            // 这个读完后，readerIndex会加1，整体的可读len会减1
            System.out.println(buf.readByte());
            // 下面这个读不会影响readerIndex的值
            System.out.println(buf.getByte(0));
            System.out.println("length（实际可读的长度）："+buf.readableBytes());
            // 使用for循环读
            for (int i = 0; i < buf.readableBytes(); i++) {
                System.out.println((char)buf.getByte(i));
            }

            // 按区间读
            // 第一个参数：index 从第几个开始读
            // 第二个参数：length 往后读取的长度是多少
            // 第三个参数：Charset 字符集对象
            System.out.println(buf.getCharSequence(0,4, CharsetUtil.UTF_8));
            System.out.println(buf.getCharSequence(4,6, CharsetUtil.UTF_8));
        }
    }
}
