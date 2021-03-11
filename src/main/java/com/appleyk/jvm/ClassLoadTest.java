package com.appleyk.jvm;

/**
 * <p>越努力，越幸运</p>
 *
 * 字符串常量池是不会存储相同内容的字符串的（jdk7之后，默认StringTableSize = 60013，且最小的值不能低于1009）
 * -XX:StringTableSize=1000
 * StringTable 适当加大，对提升性能有影响，但也不能太大
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  11:50 上午 2021/3/7
 */
public class ClassLoadTest {

    private int a = 5;
    private static int b = 10;
    private static final int c = 15;

    static {
        // clinit 类的静态代码块
        System.out.println("类的静态代码块");
    }

    {
        // init 对象（实例）的静态代码块
        System.out.println(a);
        System.out.println("对象静态代码块");

    }

    public ClassLoadTest() {
        // init 对象（实例）的构造器，对成员变量进行初始化
        System.out.println("对象构造器");
    }

    public static void main(String[] args) throws Exception {
        ClassLoadTest c = new ClassLoadTest();
        System.in.read();
    }

}
