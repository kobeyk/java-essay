package com.appleyk.io.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:24 下午 2021/1/31
 */
public class NIOServer {
    public static void main(String[] args) throws IOException {

        // 创建服务端SocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress address = new InetSocketAddress(6666);
        // 绑定端口
        serverSocketChannel.socket().bind(address);
        // 设置server通道为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 获取一个selector
        Selector selector = Selector.open();
        // 把serverSocketChannel注册到selector关心的事件为OP_ACCEPT上
        // 即，如果客户端发生连接请求，selector就会处理它
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 循环等待客户端连接
        while (true) {

            // 从selector中查看下是否有连接请求的事件发生（限制为1s）
            if (selector.select(1000) == 0) {
                System.out.println("服务器都等了1s了，还没有连接过来，那我就不阻塞了，干其他事情了...");
                continue;
            }

            // 如果有连接来的话，获取所有注册在内的key集合（注意，通过key可以拿channel）
            // 且这个集合中都是注册了相关关注的事件发生的key
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            // 遍历key，依次取出其对应的channel，建议用迭代器，后面要做remove,防止监听事件发生的channel被多次处理
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                // 获取到key
                SelectionKey selectionKey = keyIterator.next();
                // 通过key对应的通道发生的事件分别做处理
                if (selectionKey.isAcceptable()) { // OP_ACCEPT
                    // 进来了，说明有新的客户端连进来了
                    // 那就用serverSocketChannel的accept方法拿到一个客户端channel
                    // BIO中accept是阻塞的，但是这里的accept是立马执行的，因为执行下面的方法，已经知道有客户端连接了
                    // 这就是基于事件驱动的好处啊，妙啊！！！
                    SocketChannel clientChannel = serverSocketChannel.accept();
                    // 注意，要想将客户端channel注册到selector上，必须设置channel为非阻塞模式
                    clientChannel.configureBlocking(false);
                    System.out.println("from 服务端，您有新的客户端进来了："+clientChannel.hashCode());
                    // 既然拿到客户端通道了，那就把它再次注册到selector关心的事件上，并且分配一个字节缓存区
                    clientChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                } else if (selectionKey.isReadable()) { //OP_READ
                    // 如果是一个读的事件，那就通过key获取到对应的channel
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    // 通过key获取其关联的buffer（就是注册时预分配的缓冲区）
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    // 接着，将channel通道中的数据读取到这个buffer上
                    channel.read(buffer);
                    System.out.println("from 客户端：" + new String(buffer.array()));
                    // 记得复位，不然如果是同一个客户端channel多次写的话，有可能会导致position=limit
                    // 结果就是，read一直都是0，导致key的通道事件一直处于可read状态
                    buffer.clear();
                }
                // 别忘了，处理完channel后（事件发生后也处理完了），记得将注册的channel移除掉，防止重复下一次重复处理
                keyIterator.remove();
            }
        }
    }
}
