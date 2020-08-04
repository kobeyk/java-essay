package com.appleyk.thread.demo13;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class SemaphoreDemo {
    public static void main(String[] args) {

        AnotherDummy ad = new AnotherDummy();
        Thread t1 = new Thread(() -> {
            try {
                ad.one(() -> {
                    System.out.println("One");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                ad.two(() -> {
                    System.out.println("Two");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                ad.three(() -> {
                    System.out.println("Three");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();
        t1.start();
        t3.start();
    }
}
