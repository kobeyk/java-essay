package com.netty.rpc.server;

import com.netty.rpc.common.HelloService;
import com.netty.rpc.provider.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * <p>简单通道入站处理器，主要处理客户端的连接，比如负责读操作啊</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 12:53 2021/3/1
 */
public class RpcServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端"+ctx.channel().remoteAddress()+"已上线！");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端"+ctx.channel().remoteAddress()+"已下线！");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String s) throws Exception {
        System.out.println("服务端接收到消息："+s);
        String result = "";
        if (s.startsWith(HelloService.FLAG)){
            String param = s.substring(s.lastIndexOf('#')+1);
            result = new HelloServiceImpl().say(param);
        }
        ctx.writeAndFlush(result);
    }
}
