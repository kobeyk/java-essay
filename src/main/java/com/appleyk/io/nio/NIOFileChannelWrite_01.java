package com.appleyk.io.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p>越努力，越幸运</p>
 * 将字符串通过byteBuffer写入到文件通道中
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  3:12 下午 2021/1/31
 */
public class NIOFileChannelWrite_01 {
    public static void main(String[] args) throws IOException {
        String str = "Hello Appleyk,I love you!";

        // 借助jdk原生io创建一个输出流
        FileOutputStream outputStream = new FileOutputStream("/Users/apple/Appleyk/file01.txt");

        // 通过这个输出流获取对应的FileChannel(对输出流进行包装)
        // 这个fileChannel 真实的实现子类是 FileChannelImpl（FileChannel是一个抽象类，实现了Channel接口）
        FileChannel fileChannel = outputStream.getChannel();

        // 接下来，就要用到缓冲区buffer（字节buffer）,创建一个1024字节的buffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 然后，将str放入到buffer中
        byteBuffer.put(str.getBytes());

        // 将str放入buffer之后，由于position跑到了str的最后，所以要读的话，要flip（翻转，既重置position=0）
        byteBuffer.flip();

        // 将缓冲区中的数据写入到fileChannel通道中
        fileChannel.write(byteBuffer);

        // 最后，关闭流，释放资源
        outputStream.close();
    }
}
