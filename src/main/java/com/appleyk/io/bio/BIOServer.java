package com.appleyk.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  11:04 上午 2021/1/30
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        // 线程池机制

        // 思路
        // 1. 创建一个线程池
        // 2. 如果有客户端连接，就create一个thread，与之通信

        // 无限大的可缓存的线程池，没有任务队列，一旦有任务来了，如果没有空闲的线程，就new新线程
        // 如果没有任务，线程超过60s会被回收，适用场景，短小快的任务，来也匆匆去也匆匆
        ExecutorService cachePool = Executors.newCachedThreadPool();

        // 创建服务端socket
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务端6666启动监听");
        System.out.println("等待连接....");
        // 轮询监听，处理客户端请求
        while (true) {
            // 阻塞，知直到有客户端连接
            final Socket client = serverSocket.accept();
            System.out.println("连接一个客户端");
            // 连接交给线程池来处理
            cachePool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("线程ID：" + Thread.currentThread().getId()
                            + ",线程name：" + Thread.currentThread().getName());
                    // 手写一个handler来处理
                    handler(client);
                    while (true) {
                        if (client.isClosed()) {
                            System.out.println("客户端已下线");
                            try {
                                client.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }
                }
            });
        }
    }

    // 与客户端进行通信
    private static void handler(Socket client) {
        // 获取客户端输入流,如果异常，会自动关闭对应的资源（比如客户端socket的close）
        try (InputStream inputStream = client.getInputStream()) {
            byte[] data = new byte[1024];
            int len;
            // 如果客户端连接上了，但是没有send消息，则服务端线程会阻塞
            System.out.println("read...");
            while ((len = inputStream.read(data)) != -1) {
                // 这里，还要额外判断下，客户端socket是否下线了
                // 看下，处理read的线程是不是和处理accept客户端连接的线程是同一个
                System.out.println("线程ID：" + Thread.currentThread().getId()
                        + ",线程name：" + Thread.currentThread().getName());
                // 输出客户端send的数据（mac 用telnet模拟，control+] 组合键启动字符发送状态）
                System.out.println("服务接收到来自客户端的消息：" + new String(data, 0, len));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
