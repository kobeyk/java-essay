package com.appleyk.string;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  11:35 上午 2020/9/1
 */
public class StrJava {
    public static void main(String[] args) {
        String s1 = new StringBuilder("ja").append("va").toString();
        System.out.println(s1 == s1.intern());
    }
}
