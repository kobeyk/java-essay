package com.appleyk.jmm;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class MethodStacksDemo {
    static int a = 0;
    public static void add(){
        a++;
    }
    public static void show(){
        System.out.println(a);
    }

    public static void main(String[] args) {
        add();
        show();
        System.out.println("-- --");
    }
}
