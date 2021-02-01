package com.appleyk.io.nio.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * <p>越努力，越幸运</p>
 * 聊天室客户端
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  8:19 下午 2021/2/1
 */
public class GroupChatClient {

    private static Logger logger = LoggerFactory.getLogger(GroupChatClient.class);

    private final String host = "127.0.0.1";
    private final int port = 6666;
    private Selector selector;
    private SocketChannel socketChannel;
    // 客户端名字
    private String clientName;

    // 客户端初始化
    public GroupChatClient() {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress(host, port));
            socketChannel.configureBlocking(false);
            // 将客户端通道注册到选择器上，并指定关心的事件为读
            socketChannel.register(selector, SelectionKey.OP_READ);
            // 得到客户端name（其实就是open后对象的地址）
            clientName = socketChannel.getLocalAddress().toString().substring(1);
            // 提示一句话
            logger.info("{} is ready ...", clientName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 发送消息到服务端
    public void sendInfo(String message) {
        message = clientName + " 说：" + message;
        try {
            // 直接将buffer写入到客户端通道里
            socketChannel.write(ByteBuffer.wrap(message.trim().getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 从服务端通道读取消息
    public void readInfo() {
        try {
            // 很简单，直接取出keys,采用阻塞的方式取，因为是案例，不可能等待的过程还是干其他事情，没必要
            int selectCount = selector.select();
            // 如果大于0，说明监听的读事件发生了，即服务端往客户端通道转发消息了
            if (selectCount > 0) {
                // 取出发生事件的key
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    //  判断key对应的监听事件
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        // 开辟一个buffer
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        String message = new String(byteBuffer.array());
                        logger.info("{}", message.trim());
                    }
                    // 一定不要忘了移除key
                    iterator.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GroupChatClient chatClient = new GroupChatClient();
        // 一个线程循环读数据
        new Thread(() -> {
            while (true) {
                chatClient.readInfo();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        // 主线程来模拟发送数据
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            // 获取用户的键盘输入数据
            String msg = scanner.nextLine();
            logger.info("我说：{}",msg);
            chatClient.sendInfo(msg);
        }
    }
}
