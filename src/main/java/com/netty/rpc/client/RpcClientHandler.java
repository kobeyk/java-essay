package com.netty.rpc.client;

import com.appleyk.io.netty.rpc.service.HelloService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.Callable;

/**
 * <p></p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 13:27 2021/3/1
 */
public class RpcClientHandler extends SimpleChannelInboundHandler<String>
        implements Callable<Object> {

    private ChannelHandlerContext taskContext;
    private String result;
    private String param ;

    @Override
    protected synchronized void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        // 读取服务端响应数据
        this.result = s;
        // 一旦服务端响应了，立马通知阻塞的线程
        notify();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.taskContext = ctx;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public synchronized Object call() throws Exception {
        if("gun".equals(param) || "滚".equals(param)){
            // 没什么好商量的，直接关闭客户端通道
            taskContext.close();
            System.out.println("====== Rpc Client Close !======");
            return null;
        }
        taskContext.writeAndFlush(HelloService.PROTOCOL+param);
        wait();
        return result;
    }
}
