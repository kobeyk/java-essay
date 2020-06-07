package com.appleyk.tomcat;

import com.appleyk.tomcat.config.CServletMapping;
import com.appleyk.tomcat.config.CServletMappingConfig;
import com.appleyk.tomcat.request.CRequest;
import com.appleyk.tomcat.request.CResponse;
import com.appleyk.tomcat.servlet.CServlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>越努力，越幸运 -- 模拟tomcat启动（完成初始化、请求分发等功能）</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class TomcatApp {

    /**Socket启动时监听的端口号*/
    private Integer port;

    /** 模拟Tomcat，必须要有Socket套接字，没有的话，无法实现地址监听和模拟消息的接收和响应*/
    private ServerSocket serverSocket;

    /** 请求url和servlet类完全限定名之间的映射关系*/
    private Map<String, String> urlServletMapping;

    public TomcatApp() {
    }

    public TomcatApp(Integer port) {
        this.port = port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void start() {
        // 调用下初始化,模拟处理url和servlet之间的映射关系
        init();
        try {
            serverSocket = new ServerSocket(this.port);
            System.out.println("Tomcat is starting...");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                if(clientSocket.isClosed()){
                    System.out.println("客户端Socket处于关闭状态...");
                    break;
                }
                InputStream inputStream = clientSocket.getInputStream();
                OutputStream outputStream = clientSocket.getOutputStream();
                // 通过客户端的输入流对象，实例化自定义的request对象
                CRequest request = new CRequest(inputStream);
                System.out.println("客户端请求request = " + request);
                // 接下来，分发请求
                dispatch(request, new CResponse(outputStream));
                clientSocket.close();
            }
        } catch (IOException e) {

        } finally {
            // 最后如果程序退出，别忘了释放服务端的socket资源
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {

                }
            }
        }
    }

    public void init() {
        urlServletMapping = new HashMap<>();
        // 遍历ServletMappingConfig，处理下，url和servlet之间的关系
        for (CServletMapping mapping : CServletMappingConfig.mappings) {
            urlServletMapping.put(mapping.getUrl(), mapping.getClassName());
        }
    }

    public void dispatch(CRequest request, CResponse response) {
        // 1、先拿到请求的url
        String url = request.getUrl();
        // 2、根据url匹配到具体的servlet（注意，是servlet所在类的完全限定名 ）
        String servletClass = urlServletMapping.get(url);
        // 3、判断下，如果url找不到对应的servlet，说明api接口地址不存在，直接写404返回
        if (servletClass == null) {
            System.out.println("哎哟我去，你请求的地址不存在哦！");
            response.write("404,请求的页面不存在！");
            return;
        }
        // 4、如果存在的话，通过Java反射技术，拿到servlet的类
        try {
            Class<?> sClass = Class.forName(servletClass);
            // 4.1 通过sClass new 实例
            CServlet servlet = (CServlet) sClass.newInstance();
            // 4.2 如果正常拿到的话，执行servlet的service方法来处理请求
            servlet.service(request, response);
        } catch (ClassNotFoundException e) {
            System.out.println("ServletClass：" + servletClass + ",找不到对应的类");
            response.write("ServletClass：" + servletClass + ",找不到对应的类");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TomcatApp(8099).start();
    }
}
