package com.appleyk.io.netty.rpc.netty.client;

import com.appleyk.io.netty.rpc.service.HelloService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Callable;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  6:43 下午 2021/2/28
 */
public class RpcClientChannelHandler extends SimpleChannelInboundHandler<String> implements Callable {

    /**
     * 异步任务执行时，需要用到当前通道的上下文对象
     */
    private ChannelHandlerContext taskContext;

    /**
     * 外界传进来的参数，这个参数需要在调用远程api的时候，传递进去
     */
    private String param;

    /**
     * 异步请求服务端，通过通道读取到的同步响应结果
     */
    private String result;

    /**
     * 当客户端通道与服务端建立连接就绪时，会调用下面的方法（仅会调用一次）
     * @param ctx 通道处理器上下文对象
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 初始化，将当前的ctx赋给taskContext
        System.out.println("=== channelActive方法被调用了");
        this.taskContext = ctx;
    }

    @Override
    protected synchronized void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("=== 客户端收到服务端发送来的消息： " + msg);
        result = msg;
        // 一旦客户端接收到消息，就意味着，请求调用结束了，此次必须将之前异步执行的任务通知下，让他取结果
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("=== 服务消费方异常了 ===");
        System.out.println(cause.getMessage());
    }

    /**
     * 外界将参数传进来
     * @param param 参数
     */
    public void setParam(String param) {
        this.param = param;
    }

    /**
     * 这个call是异步方法，需要用线程池去执行
     */
    @Override
    public synchronized String call() throws Exception {
        System.out.println("=== call方法被调用了");
        // 这个，就是给服务端发消息，将消息头+消息参数发给Netty服务提供方
        taskContext.writeAndFlush(HelloService.PROTOCOL+param);
        wait();
        return result;
    }
}
