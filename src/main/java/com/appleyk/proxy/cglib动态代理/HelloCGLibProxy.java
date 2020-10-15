package com.appleyk.proxy.cglib动态代理;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <p>越努力，越幸运，CGLib动态代理，继承机制，改写类的字节码</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:51 下午 2020/10/14
 */
public class HelloCGLibProxy implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class classz){
        // 1.设置父类为目标对象类（通过继承机制，动态改写目标类的字节码，重写父类目标方法）
        enhancer.setSuperclass(classz);
        // 2.设置回调类
        enhancer.setCallback(this);
        // 3.通过字节码动态生成目标类的代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("方法执行前，我可以干一些事情");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("方法执行后，我可以干一些事情");
        return result;
    }

    public static void main(String[] args) {
        HelloCGLibProxy proxy = new HelloCGLibProxy();
        // 拿到HelloCGLib的代理对象
        HelloCGLib cgLib = (HelloCGLib)proxy.getProxy(HelloCGLib.class);
        // 执行HelloCGLib实例的say方法，表面上执行的是目标类实例的方法，实则走的是代理类对象的方法
        cgLib.say();;
    }
}
