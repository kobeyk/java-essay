package com.appleyk.io.netty.chat.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class SimpleChatServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel sc) throws Exception {
        // ChannelPipeline类是ChannelHandler实例对象的链表，用于处理或截获通道的接收和发送数据
        // 每个新的通道Channel，都会创建一个新的ChannelPipeline，并将器pipeline附加到channel中。
        ChannelPipeline pipeline = sc.pipeline();
        // 将回车换行符作为消息结束符,解决tcp粘包问题[分隔符的拆包器]
        pipeline.addLast("framer",new DelimiterBasedFrameDecoder(2000, Delimiters.lineDelimiter()));
        // 编码，序列化，将对象序列化字节数组用于网络网络传输
        pipeline.addLast("decoder",new StringDecoder());
        // 解码, 反序列化，将接收到的字节数组反序列化成对象
        pipeline.addLast("encoder",new StringEncoder());
        // 添加我们自定义的handler，主要用来实现加入、聊天和离开
        pipeline.addLast("handler",new SimpleChatServerHandler());
        System.out.println("聊天室："+sc.remoteAddress()+"连接上了！");
    }
}

// 每一个ChannelPipeline中可以包含多个ChannelHandler。
// 所有ChannelHandler都会顺序加入到ChannelPipeline中,
// ChannelHandler实例与ChannelPipeline之间的桥梁是ChannelHandlerContext实例

//addFirst(...)    //添加ChannelHandler在ChannelPipeline的第一个位置
//addBefore(...)   //在ChannelPipeline中指定的ChannelHandler名称之前添加ChannelHandler
//addAfter(...)    //在ChannelPipeline中指定的ChannelHandler名称之后添加ChannelHandler
//addLast(...)     //在ChannelPipeline的末尾添加ChannelHandler
//remove(...)      //删除ChannelPipeline中指定的ChannelHandler
//replace(...)     //替换ChannelPipeline中指定的ChannelHandler
