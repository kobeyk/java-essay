package com.appleyk.tomcat.servlet;

import com.appleyk.tomcat.request.CRequest;
import com.appleyk.tomcat.request.CResponse;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public abstract class CServlet {

    /**
     * 请求转发，通过前端请求的http协议的方法类型，来判断，是走get还是post方法处理
     *
     * @param request  自定义http请求对象
     * @param response 自定义http响应对象
     */
    public void service(CRequest request, CResponse response) {
        if ("get".equals(request.getMethodType().toLowerCase())) {
            System.out.println("处理Get请求");
            this.doGet(request, response);
        } else if ("post".equals(request.getMethodType().toLowerCase())) {
            this.doPost(request, response);
        }
    }

    void doGet(CRequest request, CResponse response) {
        // subClass implement it and do something...
    }

    void doPost(CRequest request, CResponse response) {
        // subClass implement it and do something...
    }
}
