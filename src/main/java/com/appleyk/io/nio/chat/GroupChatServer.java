package com.appleyk.io.nio.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>越努力，越幸运</p>
 * 群聊聊天室服务端
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  7:45 下午 2021/2/1
 */
public class GroupChatServer {
    Logger logger = LoggerFactory.getLogger(GroupChatServer.class);
    // 定义多路复用器
    private Selector selector;
    // 定义服务端通道
    private ServerSocketChannel listenerChannel;
    // 定义端口号
    private static final int port = 6666;

    // 初始化
    public GroupChatServer() {
        try {
            // 获取选择器
            selector = Selector.open();
            // 获取serverSocketChannel
            listenerChannel = ServerSocketChannel.open();
            // 设置非阻塞
            listenerChannel.configureBlocking(false);
            // 绑定地址
            listenerChannel.socket().bind(new InetSocketAddress(port));
            // 将服务端通道注册到selector上，并设置关心事件
            listenerChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 循环监听
    public void listen() throws IOException {
        logger.info("服务端已开启监听...");
        while (true) {
            if (selector.select(2000) == 0) {
                //logger.info("服务端等待客户端连接...");
                // 没有连接进来，就继续跳出循环监听...
                continue;
            }
            // 如果有连接，那就获取到所有被选择（发生监听事件）的keys
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                // 获取发生事件的key
                SelectionKey selectionKey = iterator.next();
                // 判断key的事件，针对不同事件做处理
                if (selectionKey.isAcceptable()) { // OP_ACCEPT
                    SocketChannel clientChannel = listenerChannel.accept();
                    // 设置非阻塞
                    clientChannel.configureBlocking(false);
                    // 注册读事件
                    clientChannel.register(selector, SelectionKey.OP_READ);
                    // 给出提示
                    logger.info("客户端{} 已上线", clientChannel.getRemoteAddress());
                } else if (selectionKey.isReadable()) { // OP_READ
                    // 处理读，专门封装一个handler去处理
                    readHandler(selectionKey);
                }
                // 清除key，防止重复处理
                iterator.remove();
            }
        }
    }

    // 从客户端通道读取数据到buffer
    private void readHandler(SelectionKey key) {
        // 先定义一个socketChannel
        SocketChannel socketChannel = null;
        try {
            // 获取key关联的通道
            socketChannel = (SocketChannel) key.channel();
            // 创建字节buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 从客户端通道读取数据到字节缓存区
            int read = socketChannel.read(byteBuffer);
            if (read > 0) {
                // 把缓冲区的数据输出
                String msg = new String(byteBuffer.array());
                logger.info("from 客户端 {}", msg);
                // 向其他的客户端转发消息,写一个方法来处理（别忘了排除自己）
                send2OtherClients(msg, socketChannel);
            }
            // 如果读到的是-1，说明客户端已经挂了
            if (read == -1) {
                destroy(key,socketChannel);
            }
        } catch (IOException e) {
            // 如果此时客户端突然断开连接，channel读数据会抛异常进到这里
            try {
                destroy(key,socketChannel);
            } catch (IOException ie) {
                ie.printStackTrace();
            }
        }
    }

    public void destroy(SelectionKey key,SocketChannel channel) throws IOException{
        logger.info("客户端 {} 退出群聊", channel.getRemoteAddress());
        // 取消key的注册
        key.cancel();
        // 关闭客户端通道
        channel.close();
    }

    /**
     * 转发消息给其他客户端（通道）
     *
     * @param message 转发的消息
     * @param self    发送消息的源客户端通道
     * @throws IOException
     */
    private void send2OtherClients(String message, SocketChannel self) throws IOException {

        logger.info("服务器开始向客户端转发消息了...");
        // 遍历所有注册到selector上的socketChannel，并排除self
        for (SelectionKey selectionKey : selector.keys()) {
            // 通过key取出其对应的通道
            Channel targetChannel = selectionKey.channel();
            // 如果不是发送消息的本尊，服务器就把消息转发给其他客户端通道(注意target有可能是服务端本身)
            if (targetChannel instanceof SocketChannel && !self.equals(targetChannel)) {
                SocketChannel socketChannel = (SocketChannel) targetChannel;
                // 包装一个byteBuffer
                ByteBuffer byteBuffer = ByteBuffer.wrap(message.getBytes());
                // 将buffer写入通道
                socketChannel.write(byteBuffer);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatServer chatServer = new GroupChatServer();
        // 服务端开启监听（等待连接事件、等待读事件发生处理消息）
        chatServer.listen();
    }
}
