package com.appleyk.aop;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:50 下午 2020/12/7
 */
public class Son1 extends Father{
    public static void main(String[] args) {
        Father son1 = new Son1();
        System.out.println(son1.age);
    }
}
