package com.appleyk.io.nio.review;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:10 下午 2021/1/31
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        // 注册，很关键,服务端socket管道主要注册的就是accept事件，监听客户端连接
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 接着就是循环监听了
        while (true){
            // 首先，取发生事件的通道个数，如果==0，说明没有客户端连接
            if (selector.select(3000) == 0){
                //System.out.println("服务端等待3s，没有客户端连接...,我去干其他事情了");
                //System.out.println("我去泡妹去了，等回再来看一遍有没有accept事件发生");
                continue;
            }
            // 如果有，拿到所发生事件的所有keys
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 遍历，用迭代器遍历，因为迭代器的remove是线程安全的
            // 其他for遍历在多线程下，如果remove的同时有集合的更新操作，会抛ConcurrentModificationException
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey selectionKey = keyIterator.next();
                if(selectionKey.isAcceptable()){
                    // 如果有连接进来了，通过accept获取一个客户端通道
                    SocketChannel clientChannel = serverSocketChannel.accept();
                    clientChannel.configureBlocking(false);
                    System.out.println("from 服务端，您有新的客户端连进来了："+clientChannel.hashCode());
                    // 接着，要将当前的客户端通道注册到selector上了，不过这次是read事件，因为连上了，剩下的就是读啊
                    // 注意，第三个参数是这个通道上关联的buffer，也就是数据附件
                    clientChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }else if (selectionKey.isReadable()){
                    // 如果客户端向服务端发来了数据，就处理
                    SocketChannel clientChannel = (SocketChannel)selectionKey.channel();
                    // 获取key关联的buffer
                    ByteBuffer byteBuffer =(ByteBuffer) selectionKey.attachment();
                    // 将客户端通道中的数据读取到服务端的buffer中
                    clientChannel.read(byteBuffer);
                    // 输出客户端发生的buffer数据
                    System.out.println("from 客户端："+new String(byteBuffer.array()));
                    // 记得复位，不然如果是同一个客户端channel多次写的话，有可能会导致position=limit
                    // 结果就是，read一直都是0，导致key的通道事件一直处于可read状态
                    byteBuffer.clear();
                }
                // 每处理一个key的事件，别忘了移除掉，防止多次消费
                keyIterator.remove();
            }
        }
    }
}
