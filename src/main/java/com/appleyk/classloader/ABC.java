package com.appleyk.classloader;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:17 下午 2020/9/17
 */
public class ABC {

    {
        /**类代码块，在对象创建时调用，优先于构造方法*/
        System.out.println("2");
    }

    static {
        System.out.println("1");
    }

    public ABC(){
        System.out.println("3");
    }

    public static void main(String[] args) {
        ABC a = new ABC();
    }

}
