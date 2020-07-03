package com.appleyk.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * <p>越努力，越幸运 -- 缓存行</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class CacheLineDemo {

    // 1亿次
    private static Long COUNT = 1_0000_0000L;

    @sun.misc.Contended// 只有在启动前添加JVM参数 -XX:-RestrictContended,这个注解才生效
    private static class T{
        //private long p1,p2,p3,p4,p5,p6,p7=0L;
        public volatile long x = 0; // 内存占8字节，缓存行，一行占64字节
        //private long q1,q2,q3,q4,q5,q6,q7=0L;
    }
    public static T[] arr = new T[2];
    static {
        arr[0] = new T();
        arr[1] = new T();
    }

    public static void main(String[] args) throws Exception{
        CountDownLatch latch = new CountDownLatch(2);
        Thread t1 = new Thread(()->{
            for (int i = 0; i <COUNT ; i++) {
                arr[0].x=i;
            }
            latch.countDown();
        });
        Thread t2 = new Thread(()->{
            for (int i = 0; i <COUNT ; i++) {
                arr[1].x=i;
            }
            latch.countDown();
        });
        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        // 阻塞，直到锁栓减为0
        latch.await();
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start)+"ms");
    }
}
