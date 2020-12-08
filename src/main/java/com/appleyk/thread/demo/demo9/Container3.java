package com.appleyk.thread.demo.demo9;

import java.util.ArrayList;
import java.util.List;

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
public class Container3 {

    volatile List<Integer> cols = new ArrayList<>();

    public void add(int i) {
        cols.add(i);
        System.out.println(Thread.currentThread().getName() + " add i = " + i);
    }

    public Integer size() {
        return cols.size();
    }

    public static void main(String[] args) {
        Container3 c1 = new Container3();
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                if (c1.size() != 5) {
                    try {
                        // 释放锁
                        lock.wait();
                    } catch (Exception e) {
                    }
                }
                System.out.println("====== t2结束 =======");
            }

        }, "t2").start();
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i <10 ; i++) {
                    if(c1.size() == 5){
                        // 唤醒持有这个对象锁的线程
                        lock.notify();
                        // 但是有个问题，虽然会唤醒一个或多个处在等待状态中的线程，但是它并不会立马释放锁
                        // 这就导致，t2要想立马结束，需要等到t1全部add完之后才能被执行，这个结果已经偏离主题
                    }
                    c1.add(i);
                }
            }
        }, "t1").start();
    }
}
