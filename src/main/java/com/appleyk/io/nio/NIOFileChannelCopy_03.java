package com.appleyk.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * <p>越努力，越幸运</p>
 * 将a.txt内容通过文件通道读取到byteBuffer并写入到另一个文件输出流的文件通道中
 * 实现文件的copy
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  3:58 下午 2021/1/31
 */
public class NIOFileChannelCopy_03 {
    public static void main(String[] args) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream("a.txt");
             FileOutputStream fileOutputStream = new FileOutputStream("b.txt")) {
            FileChannel inFileChannel = fileInputStream.getChannel();
            FileChannel outFileChannel = fileOutputStream.getChannel();
            int len = 0;
            // 首先，要分配一个字节缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            // 将输入流通道中的数据读取到byteBuffer中，如果read返回值不等于-1的话，证明没读完，需要继续读
            while ((len = inFileChannel.read(byteBuffer)) != -1) {
                System.out.println("读取到len = " + len);
                // 读完后，别忘了将字节缓存区翻转一下（将position置为0）
                /**
                 *  public final Buffer flip() {
                 *       limit = position; // flip和clear方法的区别，limit最后是和读取的位置一样的
                 *       position = 0;
                 *       mark = -1;
                 *       return this;
                 *     }
                 */
                byteBuffer.flip();
                // 然后，开始将字节缓存区的内容写入输出流通道中
                // 注意，这个地方一定要特别注意，因为读写都是同一个byteBuffer
                // 由于写之前进行flip了，所以才可以从0处开始写，但是循环终止的条件是read不等于-1
                // 写完后，还要再判断一次，但是此时position = limit，这就会导致再次从输入流通道中
                // 读取字节缓存区数据时，read是等于0的，读不到内容的，因为position=limit，你要是能读到见鬼了
                // 所以，在循环内部的一开始处，一定要复位一下buffer，即调用buffer的clear方法
                outFileChannel.write(byteBuffer);
                // 写完后，别忘了将字节缓冲区复位一下，不然后面再读的话，read永远是0，就死循环出不来了
                /**
                 *  public final Buffer clear() {
                 *      position = 0;
                 *      limit = capacity; // flip和clear的区别，clear是将limit重新设置为缓存区的容量
                 *      mark = -1;
                 *      return this;
                 *     }
                 */
                byteBuffer.clear();
            }
        }
    }
}
