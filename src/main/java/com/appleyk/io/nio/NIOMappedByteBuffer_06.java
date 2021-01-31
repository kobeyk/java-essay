package com.appleyk.io.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p>越努力，越幸运</p>
 * 1、MappedByteBuffer 可以让文件直接在内存（堆外内外）修改，操作系统不需要拷贝一次（零copy）
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  5:10 下午 2021/1/31
 */
public class NIOMappedByteBuffer_06 {
    public static void main(String[] args) throws IOException {
        // 随机读取文件对象(读写模式)
        RandomAccessFile randomAccessFile = new RandomAccessFile("a.txt","rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        /**
         * 参数1： 使用读写模式
         * 参数2： 0 - 可以直接修改的起始位置
         * 参数3： 5：是映射到内存的大小，即将1.txt的多个字节映射到内存,可以直接修改的范围是0~5（最多可以修改5个字节）
         * MappedByteBuffer是抽象类，其实现子类是DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0,(byte) 'H');
        mappedByteBuffer.put(4,(byte) '9');
        // 如果超过映射内存字节数的范围，会抛IndexOutOfBoundsException异常
        // mappedByteBuffer.put(5,(byte) 'o');
        System.out.println("修改文件内容成功！");
        randomAccessFile.close();

    }
}
