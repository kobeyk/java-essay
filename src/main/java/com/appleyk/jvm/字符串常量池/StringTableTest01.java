package com.appleyk.jvm.字符串常量池;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  6:42 下午 2021/3/11
 */
public class StringTableTest01 {
    public static void main(String[] args) {
        System.out.println();
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
        // 从下面开始，字符串常量池中的str的个数不在增加，因为上面已经有了！
        System.out.println("1");
        System.out.println("2");
        System.out.println("3");
        System.out.println("4");
        System.out.println("5");
    }
}
