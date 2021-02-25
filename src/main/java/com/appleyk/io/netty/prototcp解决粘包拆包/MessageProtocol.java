package com.appleyk.io.netty.prototcp解决粘包拆包;

/**
 * <p>越努力，越幸运</p>
 * 自定义消息协议
 * processOn示意图地址：https://processon.com/view/60364b14e0b34d1244395440
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:19 下午 2021/2/24
 */
public class MessageProtocol {

    /**消息的长度（关键）*/
    private int len;
    /**消息的内容*/
    private byte[] content;

    public MessageProtocol(byte[] content) {
        this.content = content;
        this.len = content.length;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
