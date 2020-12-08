package com.appleyk.thread.demo.demo5;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Demo5 {


    // 保证原子性操作
    AtomicInteger count = new AtomicInteger(0);

    public  void test() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        Demo5 demo3 = new Demo5();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(() -> demo3.test(), ""+(i+1)));
        }
        threads.forEach(t -> t.start());
        threads.forEach(t -> {
                    try {
                        t.join();
                    } catch (Exception e) {

                    }
                }
        );
        System.out.println(demo3.count);
    }

}
