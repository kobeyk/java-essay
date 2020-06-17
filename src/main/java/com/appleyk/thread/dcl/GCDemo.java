package com.appleyk.thread.dcl;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class GCDemo {
    public static GCDemo gc2 = new GCDemo();
    public static final GCDemo gc3 = new GCDemo();
    public GCDemo getGCDemo1(){
        GCDemo gc1 = new GCDemo();
        return gc1;
    }
    // gc1 : 虚拟机栈中局部变量引用的对象
    // gc2 : 方法区中类的静态变量引用的对象
    // gc3 : 方法区中类的常量引用的对象
}
