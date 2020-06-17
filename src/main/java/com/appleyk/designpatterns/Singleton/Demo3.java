package com.appleyk.designpatterns.Singleton;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Demo3 {
    private Demo3(){ }
    // 静态内部类
    public static class SingletonInner{
        // JVM保证，instance在内存中只会new一个实例，不会出现多个
        public static final Demo3 instance = new Demo3();
    }
    public static Demo3 getInstance(){
         return SingletonInner.instance;
    }
}
