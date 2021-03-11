package com.appleyk.jvm.字符串常量池;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:15 下午 2021/3/11
 */
public class StringIntern03 {
    public static void main(String[] args) {
        String x = "ab";
        // 首先，常量池中并没有ab字面量
        String s = new String("a") + new String("b");

        /**
         * jdk6：s1就是常量池ab的地址，s1指向字符串常量池 jdk6：true false
         * jdk7: 同上
         * 结论：地址一不一样，就看字符串常量池存放的是地址引用啊，还是真实的值啊
         */
        String s1  =  s.intern();
        System.out.println(s1 == "ab");// jdk6:true  jdk7/8:true
        System.out.println(s == "ab");// jdk6:false  jdk7/8:false
    }
}
