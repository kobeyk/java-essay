package com.appleyk.io.zerocopy.NIO零拷贝;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:16 下午 2021/2/3
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",6666));
        String fileName ="/Users/apple/Appleyk/学习资料/_深入理解Java虚拟机_JVM高级特性与最佳实践 第2版_220_.pdf";
        FileInputStream inputStream = new FileInputStream(fileName);
        FileChannel fileChannel = inputStream.getChannel();

        // linux调用transferTo方法是一次性传输的
        // 而windows调一次只能发送8M，需要分段发送
        long start = System.currentTimeMillis();
        long len = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        long end = System.currentTimeMillis();
        System.out.println("发送总字节数："+len+",耗时："+(end-start));
        fileChannel.close();
        inputStream.close();
    }
}
