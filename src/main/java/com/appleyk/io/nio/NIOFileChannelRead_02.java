package com.appleyk.io.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p>越努力，越幸运</p>
 * 读取文件，并显示（从通道读数据到buffer，并将字节buffer中的字节数组转成字符串输出）
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  3:38 下午 2021/1/31
 */
public class NIOFileChannelRead_02 {
    public static void main(String[] args) throws IOException {

        // 创建文件对象
        File file = new File("/Users/apple/Appleyk/file01.txt");
        // 创建一个文件输入流
        FileInputStream inputStream = new FileInputStream(file);

        // 通过输入流对象获取对应的fileChannel
        FileChannel fileChannel = inputStream.getChannel();

        // 创建字节buffer,容量为文件的内容长度
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        // 通过channel读取buffer
        // read：从通道读取数据到缓冲区
        fileChannel.read(byteBuffer);

        // 将字节缓存区内容转换成字符串
        System.out.println(new String(byteBuffer.array()));

        // 最后，关闭输入流
        inputStream.close();

    }

}
