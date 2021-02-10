package com.appleyk.io.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:32 下午 2021/2/10
 */
public class NettyByteBuf01 {
    public static void main(String[] args) {
        // 创建一个ByteBuf
        // 说明
        // 1,创建对象，该对象包含一个数组arr，是一个byte[10]
        // 2,在netty的ByteBuf中，不需要使用flip进行读写翻转
        //   因为底层维护了一个readerIndex和writerIndex
        // readerIndex：下一个要读的位置（只有调用了readByte()才会递增，而getByte(index)不会）
        // writerIndex：下一个要写的位置
        // 3，通过readerIndex、writerIndex、capacity，将buffer分成了三个区域
        // 0--readerIndex :已经读取的区域
        // readerIndex--writerIndex，可读的区域
        // writerIndex--capacity ，表示可写的区域
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            buffer.writeByte(i);
        }
        System.out.println("capacity = "+buffer.capacity());
        // 输出
        for (int i = 0; i <buffer.capacity() ; i++) {
            System.out.println(buffer.getByte(i));
        }
        System.out.println("通过readByte方法获取值");
        System.out.println("buffer是否可读："+buffer.isReadable());
        for (int i = 0; i < buffer.capacity() ; i++) {
            System.out.println(buffer.readByte());
        }
        // 读完了，readerIndex=9了，导致不能再读了
        /**
         *   public boolean isReadable() {
         *         return this.writerIndex > this.readerIndex;
         *   }
         *   此时writerIndex == readerIndex
         */
        System.out.println(buffer.isReadable());
    }
}
