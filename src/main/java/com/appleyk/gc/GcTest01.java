package com.appleyk.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Full GC测试</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class GcTest01 {
    byte[] a = new byte[1024*100]; // 100kb
    public static void main(String[] args) {
        // names是线程栈的局部变量，它是gcroot
        List<GcTest01> names = new ArrayList<>();
        while (true) {
            names.add(new GcTest01());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}
