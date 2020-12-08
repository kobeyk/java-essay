package com.appleyk.thread.demo.demo8;

import java.util.concurrent.TimeUnit;

/**
 * <p>面试题：t1和t2会一起启动吗？</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Demo8 {

    String str1 = "hello";
    String str2 = "hello";

    // 答案：不能，因为字符串常量池，str1 等于 str2

    public void test1() {
        synchronized (str1) {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " start ...");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {

                }
            }
        }
    }

    public void test2() {
        synchronized (str2) {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " start ...");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {

                }
            }
        }
    }

    public static void main(String[] args) {
        Demo8 demo8 = new Demo8();
        new Thread(demo8::test1,"t1").start();
        new Thread(demo8::test2,"t2").start(); }

}
