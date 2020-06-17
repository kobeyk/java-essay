package com.appleyk.designpatterns.Singleton;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Demo1 {
    private Demo1(){}
    private static Demo1 instance = new Demo1();
    public static Demo1 getInstance(){
        return instance;
    }
}
