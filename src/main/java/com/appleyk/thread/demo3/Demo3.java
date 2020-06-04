package com.appleyk.thread.demo3;

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
public class Demo3 {

    // 这玩意只保证可见性，不保证线程安全
    volatile int count = 0;

    public void test() {
        System.out.println(Thread.currentThread().getName()+" start...");
        for (int i = 0; i < 10000; i++) {
            count++;
        }
        System.out.println(Thread.currentThread().getName()+" end...");
    }

    public static void main(String[] args) {
        Demo3 demo3 = new Demo3();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> demo3.test(), ""+(i+1)));
        }
        threads.forEach(t -> t.start());
        threads.forEach(t -> {
                    try {
                        // join的作用就是,就是让当前主线程放弃cpu控制权，只有当所有的线程执行完了，才轮到main线程执行
                        t.join();
                    } catch (Exception e) {

                    }
                }
        );
        System.out.println(demo3.count);
    }

}
