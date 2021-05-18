package com.appleyk.jvm.字符串常量池;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:02 下午 2021/3/11
 */
public class StringIntern02 {
    public static void main(String[] args) {

        // 首先，常量池中并没有ab字面量
        String s = new String("a") + new String("b");

        /** 其次，在jdk7之后，s.intern()，显然ab不存在，但是堆上有这个对象啊，s1必须和s指向的地址一样
         *  而放入常量池的ab对象，本身指向的就是堆上new String("ab")的地址，因此s1 == s2 == "ab"
         *  而在jdk6中， 在字符串常量池中ab是new String("ab")对象的复制
         *  因此，s1和ab地址是一样的，但是s指向的是堆，而s1、ab指向的是常量池中的地址
         */
        String s1  =  s.intern();
        System.out.println(s1 == "ab");// jdk6:true  jdk7/8:true
        System.out.println(s == "ab");// jdk6:false  jdk7/8:true

    }
}
