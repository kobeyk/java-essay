package com.appleyk.io.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * <p>越努力，越幸运</p>
 * 使用通道的transferFrom进行（小）文件的copy
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  4:47 下午 2021/1/31
 */
public class NIOFileChannelTransfer_04 {
    public static void main(String[] args) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream("src.png");
             FileOutputStream fileOutputStream = new FileOutputStream("tar.png")) {
            FileChannel srcChannel = fileInputStream.getChannel();
            FileChannel tarChannel = fileOutputStream.getChannel();
            // 直接使用transfer来copy
            tarChannel.transferFrom(srcChannel,0,srcChannel.size());
        }
    }
}
