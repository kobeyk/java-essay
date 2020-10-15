package com.appleyk.proxy.jdk动态代理;

import java.lang.reflect.Proxy;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:00 下午 2020/10/14
 */
public class HelloJDKImpl1 implements HelloJDK1,HelloJDK2 {

    @Override
    public void say1() {
        System.out.println("say1:hello world! -- from 1");
    }

    @Override
    public void say2() {
        System.out.println("say2:hello world! -- from 1");
    }

    public static void main(String[] args) {
        HelloJDK1 helloJDK1 =(HelloJDK1) Proxy.newProxyInstance(HelloJDKImpl1.class.getClassLoader(),
                new Class[]{HelloJDK1.class,HelloJDK2.class},
                new HelloJDKInvocationHandler(new HelloJDKImpl1()));
        helloJDK1.say1();
        System.out.println("=============== ===============");
        HelloJDK2 helloJDK2 =(HelloJDK2) Proxy.newProxyInstance(HelloJDK2.class.getClassLoader(),
                new Class[]{HelloJDK2.class},
                new HelloJDKInvocationHandler(new HelloJDKImpl1()));
        helloJDK2.say2();
    }
}
