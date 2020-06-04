package com.appleyk.thread.demo6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>面试题：多个atomic对象连续调用能够构成原子性？  答案是否的：不能保证</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Demo6 {


    // 保证原子性操作
    AtomicInteger count = new AtomicInteger(0);

    public  void test() {
        for (int i = 0; i < 10000; i++) {
            if(count.get()<2000){
                count.incrementAndGet();
            }
        }
    }

    public static void main(String[] args) {
        Demo6 demo3 = new Demo6();
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
