package com.appleyk.designpatterns.Singleton;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Demo2 {
    private Demo2(){}
    private volatile Demo2 instance = null;
    public Demo2 getInstance(){
        if(instance == null){
            synchronized (Demo2.class){
                if(instance == null){
                    instance = new Demo2();
                }
                return instance;
            }
        }
        return instance;
    }
}
