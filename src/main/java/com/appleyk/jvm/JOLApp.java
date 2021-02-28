package com.appleyk.jvm;

import org.openjdk.jol.info.ClassLayout;

/**
 * <p></p>
 *
 * @author yukun24@126.com
 * @version V.0.2.2-refactor
 * @company 苏州中科蓝迪
 * @date created on 11:19 2020/10/26
 */
public class JOLApp {
    private static int age = 10;
    public static void main(String[] args) {
        int a  = 6,b = 3;
        int c = a+b;
        System.out.println(c);
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

    /**
     * 局部变量表每个存放变量的单元叫slot（插槽）
     * 下面演示了slot的重复利用
     */
    public void A(){
        int a =1;
        {
            int b;
            b = a+2;
        }
        // double 和 long 各占两个slot
        double salary = 10.0d;
        long count = 10L;
        int c = 3 + a;
    }
}
