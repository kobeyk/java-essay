package com.appleyk.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * <p>越努力，越幸运</p>
 * buffer的分散和聚合操作
 * scattering:将数据写入到buffer时，可以采用buffer数组依次写入
 * 一个buffer满了，写下一个
 * gethering：从buffer读取数据时，可以采用buffer数组依次读，
 * 如果第一个读完了，就读下一个
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  5:26 下午 2021/1/31
 */
public class ScatteringAndGethering {
    public static void main(String[] args) throws IOException {

        // 使用ServerSocketChannel 和 SocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(6666);
        // 绑定本机的6666端口
        serverSocketChannel.socket().bind(inetSocketAddress);
        // 创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        System.out.println("服务端已启动，等待客户端连接....");
        SocketChannel socketChannel = serverSocketChannel.accept();
        // 假设从客户端读取的消息字节长度为8
        int messageLen = 8;
        while (true) {
            int byteRead = 0;
            // 如果每次读取的字节小于消息的长度的话
            while (byteRead < messageLen) {
                // 从客户端的socket通道中读取数据到buffer数组（自动会分散到每一个buffer元素）
                System.out.println("read...");
                long read = socketChannel.read(byteBuffers);
                // 累计读取的字节数
                byteRead += read;
                System.out.println("byteRead : " + byteRead);
                // 看一眼position和limit的位置（必然和每个buffer的capacity保持一致的）
                Arrays.asList(byteBuffers).stream().map(b -> "position = " + b.position() +
                        ",limit = " + b.limit()).forEach(System.out::println);
            }
            // 读完后，就要回写到客户端了,所以要翻转，将buffers换成读
            Arrays.asList(byteBuffers).stream().forEach(ByteBuffer::flip);

            long byteWrite = 0;
            while (byteWrite < messageLen) {
                // 这个会自动聚合
                long write = socketChannel.write(byteBuffers);
                byteWrite += write;
            }
            // 复位下，下次接着读,不然会死循环的，因为写完后，上面的position=limit，再次读的话，read=0，永远都比messageLen小，永远都循环
            Arrays.asList(byteBuffers).stream().forEach(ByteBuffer::clear);
            System.out.println("readBytes = " + byteRead + ",writeBytes = " + byteWrite + ", messageLen = " + messageLen);
        }
    }
}
