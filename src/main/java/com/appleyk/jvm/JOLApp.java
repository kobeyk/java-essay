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
    public static void main(String[] args) {
        int a  = 6,b = 3;
        int c = a+b;
        System.out.println(c);
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

    }
}
