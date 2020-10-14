package com.appleyk.proxy.jdk动态代理;

import java.lang.reflect.Proxy;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:13 下午 2020/10/14
 */
public class HelloJDKImpl2 implements HelloJDK1 {

    @Override
    public void say1() {
        System.out.println("say1:hello world! -- from 2");
    }

    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        HelloJDK1 helloJDK2 = (HelloJDK1)Proxy.newProxyInstance(HelloJDKImpl2.class.getClassLoader(),
                new Class[]{HelloJDK1.class},
                new HelloJDKInvocationHandler(new HelloJDKImpl2()));
        helloJDK2.say1();
    }
}
