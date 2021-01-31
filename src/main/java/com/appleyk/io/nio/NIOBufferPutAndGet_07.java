package com.appleyk.io.nio;

import java.nio.ByteBuffer;

/**
 * <p>越努力，越幸运</p>
 * buffer的put和get的数据类型是一一对应的，按照顺序来读写的
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  4:59 下午 2021/1/31
 */
public class NIOBufferPutAndGet_07 {
    public static void main(String[] args) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        //  放不同类型的数据
        byteBuffer.putInt(1);
        byteBuffer.putShort((short)2);
        byteBuffer.putChar('a');
        byteBuffer.putLong(3L);

        // 翻转
        byteBuffer.flip();

        // 如果读取的时候类型不匹配会抛出BufferUnderflowException异常
        System.out.println(byteBuffer.getInt());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getLong());
        System.out.println(byteBuffer.getLong());
    }
}
