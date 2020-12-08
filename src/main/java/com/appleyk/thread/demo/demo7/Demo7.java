package com.appleyk.thread.demo.demo7;

import java.util.concurrent.TimeUnit;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Demo7 {
    private Object o = new Object();

    public void test() {
        synchronized (o) {
            // t1 线程模拟死循环，模拟锁一直占用不释放的情况
            while (true) {
                System.out.println(Thread.currentThread().getName() + " start...");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (Exception e) {

                }
                System.out.println(Thread.currentThread().getName() + " end...");
            }

        }
    }

    public static void main(String[] args) {
        Demo7 demo7 = new Demo7();
        new Thread(demo7::test, "t1").start();
        new Thread(demo7::test, "t2").start();
        // 考虑下，如果加了下面这行代码，t2线程会执行吗？ 为什么？
        demo7.o = new Object();

        // 答案是肯定的，因为test方法中，同步块上上锁的对象是o，这里new了一个新对象，说明锁的对象都换了
        // 所以t2等于拿到了一把未上锁的对象
    }
}
