package com.appleyk.tomcat.request;

import java.io.IOException;
import java.io.OutputStream;

/**
 * <p>越努力，越幸运 -- 封装响应</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class CResponse {

    private OutputStream os;

    public CResponse(OutputStream os) {
        this.os = os;
    }

    public void write(String content) {
        if (os != null) {
            try {
                // 写http响应消息
                // HTTP/1.1 200 ok
                // Content-Type: text/html
                // html代码块
                StringBuffer sb = new StringBuffer();
                sb.append("HTTP/1.1 200 ok\n")
                        .append("Content-Type: text/html\n")
                        .append("\r\n")
                        .append("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>手写代码模拟Tomcat</title></head><body><div>")
                        .append(content)
                        .append("</div></body></html>");
                os.write(sb.toString().getBytes());
            } catch (IOException e) {
                System.out.println("往字节输出流中写内容异常！" + e.getMessage());
            } finally {
                try {
                    os.close();
                } catch (IOException e) {
                    System.out.println("关闭字节输出流异常！" + e.getMessage());
                }
            }
        }
    }

}
