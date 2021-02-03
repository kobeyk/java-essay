package com.appleyk.io.zerocopy.传统IO;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * <p>越努力，越幸运</p>
 * 传统IO客户端（读取大文件，并发送给服务端）
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  8:48 下午 2021/2/3
 */
public class OldIOClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",6666);
        String fileName = "/Users/apple/Appleyk/学习资料/_深入理解Java虚拟机_JVM高级特性与最佳实践 第2版_220_.pdf";
        FileInputStream inputStream = new FileInputStream(new File(fileName));
        // 拿到socket的输出流
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] data = new byte[4096];
        int readLen = 0;
        int total = 0;
        long start = System.currentTimeMillis();
        while ((readLen=inputStream.read(data))!=-1){
            total+=readLen;
            dataOutputStream.write(data,0,readLen);
        }
        long end = System.currentTimeMillis();
        System.out.println("发送总字节数："+total+",耗时："+(end-start));
        dataOutputStream.close();
        inputStream.close();
        socket.close();
    }

}
