package com.appleyk.io.netty.sample案例;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * <p>越努力，越幸运</p>
 * 入站的处理器适配器
 * 说明：
 * 1、我们自定义一个handler，需要继承netty规定好的某个HandlerAdapter
 * 2、这时候我们自定义一个Handler，才能称为一个handler
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  2:02 下午 2021/2/8
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    // 读取数据的事件（这里我们可以读取客户端发送的消息）--耗时的，交给定时器来处理
    // 异步执行--》将该channel放到NioEventLoop的taskQueue中，由单独的线程来执行

    /**
     * @param ctx 通道处理器上下文对象：含有管道pipeline、通道channel、连接的地址
     * @param msg 客户端发送的数据，默认是Object类型
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 1、解决方案，为了避免阻塞，我们使用自定义的普通任务来执行
        ctx.channel().eventLoop().execute(() -> {
            try {
                // 模拟耗时处理任务
                Thread.sleep(5 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello,我是服务端，汪汪汪2222", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                System.out.println("发生异常：" + e.getMessage());
            }
        });

        ctx.channel().eventLoop().execute(() -> {

            try {
                // 模拟耗时处理任务 （在之前的5s基础上加上10s，因为当前是处于一个线程的）
                Thread.sleep(10 * 1000);
                ctx.writeAndFlush(Unpooled.copiedBuffer("hello,我是服务端，汪汪汪3333", CharsetUtil.UTF_8));
            } catch (InterruptedException e) {
                System.out.println("发生异常：" + e.getMessage());
            }
        });

        // 2、用户自定义定时任务 --> 该任务是另外一个队列(scheduledTaskQueue)
        ctx.channel().eventLoop().schedule(() -> {
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello,我是服务端，汪汪汪4444", CharsetUtil.UTF_8));
        }, 5, TimeUnit.SECONDS);

        // 这里立马返回，不会等上面的执行完了后，才输出
        System.out.println("继续....");
    }

    //// 读取数据的事件（这里我们可以读取客户端发送的消息）-- 不耗时的，直接处理
    ///**
    // * @param ctx 通道处理器上下文对象：含有管道pipeline、通道channel、连接的地址
    // * @param msg 客户端发送的数据，默认是Object类型
    // * @throws Exception
    // */
    //@Override
    //public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    //    // 默认处理线程是从workerGroup里面依次取的（比如 1-2-3-1-2-3）
    //    System.out.println("服务器读取线程："+Thread.currentThread().getName());
    //    System.out.println("服务端 ctx = " + ctx);
    //    System.out.println("看看channel和pipeline的关系");
    //    Channel channel = ctx.channel();
    //    ChannelPipeline pipeline = ctx.pipeline();//本质上是一个双向链表，出站入站
    //    // 处理msg，将其转成一个ByteBuf,这个ByteBuf是Netty提供的，而NIO是ByteBuffer，注意区别
    //    // 而且Netty的ByteBuf性能更高
    //    ByteBuf buffer = (ByteBuf) msg;
    //    System.out.println("客户端发送的消息是：" + buffer.toString(CharsetUtil.UTF_8));
    //    // 从上下文中获取客户端通道，并拿到通道的地址
    //    System.out.println("客户端地址：" + channel.remoteAddress());
    //}

    // 数据读完后，触发
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // 将数据入进缓冲区并刷新（write+flush）
        // 一般来讲，我们对这个发送的数据要进行一定的编码
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello,我是服务端，汪汪汪", CharsetUtil.UTF_8));
    }

    // 如果异常会调用，发生异常就关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
