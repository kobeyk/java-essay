package com.appleyk.io.netty.inandoutboundhandler.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * <p>越努力，越幸运</p>
 * 自定义客户端入站处理器
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  3:06 下午 2021/2/23
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    @Override
    protected void channelRead0(ChannelHandlerContext ct, Long msg) throws Exception {
        // 读取服务端发过来的数据
        System.out.println("接收数据 =======");
        System.out.println("msg = "+msg);
    }

    // 当客户端通道就绪时，调用下面的方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 就绪状态下，给服务端发送一个long
        System.out.println("发送数据 ======");
        // 1、如果发送给服务端的数据是Long类型的，且只有8个字节，则服务端可以接收到
        ctx.channel().writeAndFlush(123456L);
        /**
         * 2、如果发送给服务端的数据不是Long类型的，如下
         *   2.1、发送的类型是字符串，且有16字节，则服务端的decode会调用两次
         *   2.2、而客户端这边是不会调用MyLongToByteEncoder类的encode方法
         *   2.3、因为其父类MessageToByteEncoder类中有个write方法，专门判断msg是否符合编码的类型要求的
         *   public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
         *         ByteBuf buf = null;
         *         try {
         *             if (this.acceptOutboundMessage(msg)) { // 判断msg是否是要处理的类型，是就处理，不是就直接往通道里写
         *                 I cast = msg;
         *                 buf = this.allocateBuffer(ctx, msg, this.preferDirect);
         *
         *                 try {
         *                     this.encode(ctx, cast, buf);
         *                 } finally {
         *                     ReferenceCountUtil.release(msg);
         *                 }
         *
         *                 if (buf.isReadable()) {
         *                     ctx.write(buf, promise);
         *                 } else {
         *                     buf.release();
         *                     ctx.write(Unpooled.EMPTY_BUFFER, promise);
         *                 }
         *
         *                 buf = null;
         *             } else {
         *                 ctx.write(msg, promise);
         *             }
         *         } catch (EncoderException var17) {
         *             throw var17;
         *         } catch (Throwable var18) {
         *             throw new EncoderException(var18);
         *         } finally {
         *             if (buf != null) {
         *                 buf.release();
         *             }
         *
         *         }
         *
         *     }
         */
        //ctx.channel().writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcd", CharsetUtil.UTF_8));
    }
}
