package com.appleyk.io.netty.rpc.netty.server;

import com.appleyk.io.netty.rpc.provider.HelloServiceImpl;
import com.appleyk.io.netty.rpc.service.HelloService;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  6:20 下午 2021/2/28
 */
public class RpcChannelHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端"+ctx.channel().remoteAddress()+"已上线！");
    }

    /**
     * 发生读时间，触发该方法
     * @param ctx 通道处理器上下文对象（属于pipeline中的一个双向链表中的某一个ctx）
     * @param msg 客户端传送过来的消息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {

        System.out.println("=== 服务端收到客户端"+ctx.channel().remoteAddress()+",消息内容："+msg);
        /**
         * 开始读，注意，既然是rpc框架，用户那边想调用服务端的service像在本地调用一样，必须规定msg的消息头
         * 1、拿调用HelloServer的say方法为例：暂时规定消息头前面几个必须包含"HelloService#say#"
         * 2、消息头确定后，剩下的就是客户端真正传给api的参数值了
         * 3、完整的消息如："HelloService#say#我爱你，不管海枯石烂，只要我在，我就会一直保护你！"
         */
        String result = "";
        if (msg.startsWith(HelloService.PROTOCOL)){
            // 截取msg，获取实际的参数值
            String actualParam = msg.substring(msg.lastIndexOf('#')+1,msg.length());
            HelloServiceImpl helloService = new HelloServiceImpl();
            result = helloService.say(actualParam);
        }
        // 将result返回给客户端
        ctx.writeAndFlush(result);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("=== Rpc Server 发生异常如下： ===");
        System.out.println(cause.getMessage());
    }

}
