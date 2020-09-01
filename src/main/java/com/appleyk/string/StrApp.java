package com.appleyk.string;

import org.openjdk.jol.info.ClassLayout;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class StrApp {

    public static void main(String[] args) {

        String a = "Hello World !";
        String b = "Hello World !";

        // 注意，等这玩意，要求可就高了去了，必须是一模一样的，什么意思，就是内存中a和b的地址是同一块
        // 但是呢，"Hello World!"的值是在字符串常量区，a和b分别是不同栈上的两个变量，只不过他们都指向了同一个字符串常量
        // 上句话已经揭晓了答案，a和b显然所处的栈内存地址不一样，所以 a==b 是false
        System.out.println("a==b : "+a==b);// false

        // String对象的equals这个方法，比较的是两个String对象的char value[]中的值
        // 首先判断对象是否是字符串类型，其次判断双方的数组length是不是一样
        // 最后，在逐一判断数组中的char是不是一样，都满足了返回true
        System.out.println("a.equals(b) : "+a.equals(b));

        // String的hashCode方法，是根据value[]数组中的char值进行计算的，因为a和b的value[]内容一样
        // 所以，二者的hashCode值必然也是一样的
        System.out.println("a.hashCode() == b.hashCode() : "+(a.hashCode() == b.hashCode()));

        //线程安全
        StringBuffer bf = new StringBuffer();
        bf.append(1);

        //线程不安全
        StringBuilder builder = new StringBuilder();
        builder.append(1);
        
        int i = 1;
        char c = 'a';
        long l = 2L;
        System.out.println(ClassLayout.parseInstance(i).toPrintable());
        System.out.println(ClassLayout.parseInstance(c).toPrintable());
        System.out.println(ClassLayout.parseInstance(l).toPrintable());
    }
}
