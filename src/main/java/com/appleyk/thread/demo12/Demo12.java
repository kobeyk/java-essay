package com.appleyk.thread.demo12;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Demo12 {
    public static int a, b = 0;

    public static void main(String[] args) {
        Demo12 d12 = new Demo12();

        new Thread(() -> {
            int r1 = a;
            b = 2;
            System.out.println("r1 = " + r1);
        }).start();
        new Thread(() -> {
            int r2 = b;
            a = 1;
            System.out.println("r2 = " + r2);
        }).start();
    }
}
