package com.appleyk.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class GcTest {
    byte[] a = new byte[1024*100]; // 100kb
    public static void main(String[] args) {
        // names是线程栈的局部变量，它是gcroot
        List<GcTest> names = new ArrayList<>();
        while (true) {
            names.add(new GcTest());
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}
