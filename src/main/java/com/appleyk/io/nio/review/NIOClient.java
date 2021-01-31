package com.appleyk.io.nio.review;

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
 * @date created on  10:25 下午 2021/1/31
 */
public class NIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 6666);
        // 下面就是连
        if(!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("正在努力连接服务端...");
            }
        }
        while (true){
            byte[] data = new byte[512];
            System.in.read(data);
            // 连上的话，就发数据
            //String msg = "你好啊，靓仔，能认识你一下吗？期待你的回复";
            ByteBuffer byteBuffer = ByteBuffer.wrap(data);
            socketChannel.write(byteBuffer);
            // 记得复位，便于下次从头开始写
            byteBuffer.clear();
        }
    }
}
