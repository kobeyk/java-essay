package com.appleyk.jvm.字符串常量池;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  6:59 下午 2021/3/11
 */
public class StringTableTest02 {
    public static void main(String[] args) {
        String str1 = "a"+"b"+"c";
        String str2 = "abc";
        // 由于代码会经过编译器进行优化，所以上面两个字符串是一模一样的
        System.out.println(str1 == str2);//true

        // 下面的呢？

        String str3 = "hello world";
        String str4 = "hello";
        String str5 = str4 + " world";
        // 只要涉及到变量+操作的，底层都会new出来一个新的对象放到堆中，所以下面比如是false
        System.out.println(str3 == str5);//false

        // 把str5的字面量放入到字符串常量池中，如果有，就返回该常量的地址，如果没有就添加
        String str6 = str5.intern();
        System.out.println(str6 == str3);// 显然是true，因为在常量池中，hello world是存在的

    }
}
