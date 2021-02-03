package com.appleyk.io.zerocopy.传统IO;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>除非我不想赢，否则没人能让我输</p>
 * 传统IO进行大文件的COPY服务器端
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  8:44 下午 2021/2/3
 */
public class OldIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);

        // 循环监听
        while (true){
            // 阻塞
            Socket socket = serverSocket.accept();
            // 获取客户端输入流
            DataInputStream stream = new DataInputStream(socket.getInputStream());
            String fileName = "/Users/apple/Appleyk/学习资料/1.pdf";
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            try {
                byte[] data = new byte[4096];
                int len;
                while ((len=stream.read(data))!=-1){
                    fileOutputStream.write(data);
                }
                if(len == -1){
                    fileOutputStream.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
}
