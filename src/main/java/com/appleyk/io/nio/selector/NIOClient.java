package com.appleyk.io.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:49 下午 2021/1/31
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        // 创建一个客户端通道
        SocketChannel socketChannel = SocketChannel.open();
        // 设置非阻塞
        socketChannel.configureBlocking(false);
        // 提供服务端IP和Port
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
        // 连接服务端
        if(!socketChannel.connect(address)){
            while (!socketChannel.finishConnect()){
                System.out.println("连接需要事件，客户端不会阻塞，可以干一些其他工作...");
            }
        }

        // 如果连接成功，就发送数据
        String str = "hello nio world!";
        // wrap ： 产生一个字节数据到缓存区中，大小保持一致，很方便
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        // 发送数据:将buffer写入到客户端通道中
        socketChannel.write(byteBuffer);
        // 停在屏幕的输入上，阻塞
        System.in.read();
    }
}
