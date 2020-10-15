package com.appleyk.proxy.jdk动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p>越努力，越幸运 -- JDK动态代理，委托机制，委托InvocationHandler调用目标对象的方法</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:01 下午 2020/10/14
 */
public class HelloJDKInvocationHandler implements InvocationHandler {

    private Object targetObj;
    public HelloJDKInvocationHandler(Object obj){
        this.targetObj = obj;
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] args) throws Throwable {
        System.out.println("方法执行前，我可以干一些事情");
        Object result = method.invoke(targetObj, args);
        System.out.println("方法执行后，我可以干一些事情");
        return result;
    }


}
