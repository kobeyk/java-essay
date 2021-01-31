package com.appleyk.io.nio;

import java.nio.ByteBuffer;

/**
 * <p>越努力，越幸运</p>
 * 将buffer设置为只读
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  5:03 下午 2021/1/31
 */
public class NIOBufferReadOnly_05 {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);
        for (int i = 0; i <64 ; i++) {
            byteBuffer.put((byte)i);
        }
        // 翻转
        byteBuffer.flip();
        // 将buffer转成只读的buffer
        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());
        // 读取,用while循环来读，判断只读buffer中是否有剩余的数据
        while (readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }
        // 这时候再去写，就会有问题了(ReadOnlyBufferException异常)
        readOnlyBuffer.put((byte)100);
    }
}
