package com.appleyk.tomcat.request;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>越努力，越幸运 -- 封装请求</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class CRequest {

    /**
     * 请求的url
     */
    private String url;
    /**
     * 请求的方法类型
     */
    private String methodType;

    public CRequest(InputStream is) {

        if (is == null) {
            this.url = "";
            this.methodType = "";
        } else {
            try {
                String request = "";
                byte[] bytes = new byte[1024];
                int len = 0;
                if ((len = is.read(bytes)) > 0) {
                    request = new String(bytes, 0, len);
                }

                // 打印浏览器http请求信息
                System.out.println(request);

                // GET /v1 HTTP/1.1
                // Host: localhost:8099
                // Connection: keep-alive
                // Upgrade-Insecure-Requests: 1
                // User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36
                // Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
                // .....

                String[] resArray = request.split("\r\n");
                if (resArray != null && resArray[0] != null) {
                    System.out.println("第一行信息：" + resArray[0]);
                    //  处理下第一行，分别获取请求的方法类型和url
                    String[] firstLines = resArray[0].split(" ");
                    this.methodType = firstLines[0];
                    this.url = firstLines[1];
                }
            } catch (IOException e) {
                System.out.println("读取异常");
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String toString(){
        return "{ url = "+this.url+",methodType = "+this.methodType+" }";
    }
}
