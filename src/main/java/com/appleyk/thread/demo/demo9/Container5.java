package com.appleyk.thread.demo.demo9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * <p>面试题：实现一个容器，提供两个方法：add、size
 * 写两个线程，一个线程往里面add10个数，一个线程监听，如果当前个数达到5时，线程2结束并给出提示
 * </p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Container5 {

    volatile List<Integer> cols = new ArrayList<>();

    public void add(int i) {
        cols.add(i);
        System.out.println(Thread.currentThread().getName() + " add i = " + i);
    }

    public Integer size() {
        return cols.size();
    }

    public static void main(String[] args) {
        Container5 c1 = new Container5();
        // 利用锁栓CountDownLatch(这是一个aqs实现类）
        // 当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了。
        CountDownLatch latch = new CountDownLatch(1);
        new Thread(() -> {
                if (c1.size() != 5) {
                    try {
                        // 等待锁减为0时，才继续执行
                        latch.await();
                    } catch (Exception e) {
                    }
                }
                System.out.println("====== t2结束 =======");


        }, "t2").start();
        new Thread(() -> {
                for (int i = 0; i < 10; i++) {
                    if (c1.size() == 5) {
                        latch.countDown();
                    }
                    c1.add(i);
                }
        }, "t1").start();
    }
}
