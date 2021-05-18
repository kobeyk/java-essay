package com.appleyk.jvm.字符串常量池;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:19 下午 2021/3/11
 */
public class StringIntern04 {
    public static void main(String[] args) {
        // 1、下面放开，结果在jdk6和jdk7/8中分别是false，true
        String s1 = new String("ab");
        // 2、下面放开，结果在jdk6和jdk7/8中分别是false，true
        //String s1 = new String("a")+new String("b");
        s1.intern();
        String s2 = "ab";

        /**
         *
         * 1：jdk6：s1指向的是堆地址，ab是常量池的地址，常量池存放的是ab的值，显然false
         * 2：jdk7、8：s1指向的是堆地址，由于常量池中已经存在ab了，所以s1.intern不在放入
         *   而s2指向的是常量池的地址，所以明显false
         *
         *
         * 2：
         * jdk6：s1指向的是堆地址，ab放到常量池中，重新new了一个地址，s2执行的是新的地址，因此false
         * jdk7/8: s1指向的是堆地址，ab放到常量池中，结果发现ab在堆中存在，就引用过去，所以ab指向的是堆地址，所以true
         */
        System.out.println(s1 == s2);

    }
}
