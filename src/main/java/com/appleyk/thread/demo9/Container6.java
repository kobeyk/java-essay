package com.appleyk.thread.demo9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

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
public class Container6 {

    volatile List<Integer> cols = new ArrayList<>();

    public void add(int i) {
        cols.add(i);
        System.out.println(Thread.currentThread().getName() + " add i = " + i);
    }

    public Integer size() {
        return cols.size();
    }

    public static void main(String[] args) {
        //  下面这个虽然满足题目的要求，但是实现起来很复杂
        Container6 c1 = new Container6();
        // 利用信号量Semaphore(这是一个aqs实现类）
        // 是用来控制同时访问特定资源的线程数量,记录当前还可以运行多少个资源访问资源。
        // 定义两个信号量，资源都是0，表示只有当别人release的时候，线程才可以继续执行
        Semaphore semaphore1 = new Semaphore(0);
        Semaphore semaphore2 = new Semaphore(0);
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                if (c1.size() == 5) {
                    // 如果发现size == 5,就把信号量1的permit放开，即+1，那边判断不<0,于是往下走
                    semaphore1.release();
                    try {
                        // 当等于5的时候，获取信号量的permit，发现-1<0,于是等待
                        semaphore2.acquire();
                    } catch (Exception e) {

                    }
                }
                c1.add(i);
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                // 首先获得信号量1的permit，发现-1后<0,于是乎等待
                semaphore1.acquire();
            } catch (Exception e) {

            }
            System.out.println("====== t2结束 =======");
            // t2 结束后，释放信号量2的permit
            semaphore2.release();
        }, "t2").start();
    }

}
