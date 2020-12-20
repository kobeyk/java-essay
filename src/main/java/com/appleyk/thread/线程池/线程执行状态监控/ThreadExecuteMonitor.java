package com.appleyk.thread.线程池.线程执行状态监控;

import com.appleyk.thread.线程池.ThreadPoolExecutorFactory;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>越努力，越幸运</p>
 * 通过线程工厂创建新线程的同时对线程的执行异常状态进行监控（异常捕获！！！）
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  5:10 下午 2020/12/20
 */
public class ThreadExecuteMonitor {

    private static volatile AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {

        /**
         * void uncaughtException(Thread t, Throwable e);
         */
        ThreadFactory threadFactory = (r) -> {
            Thread t = new Thread(r);
            // 给工厂创建出来的线程设置名称
            t.setName("嘻嘻" + getCount());
            //给创建的线程设置UncaughtExceptionHandler对象 里面实现异常的默认逻辑
            t.setDefaultUncaughtExceptionHandler((Thread thread, Throwable ex) -> {
                // 拿到异常，自行处理
                System.out.println(thread.getName() + "-线程执行任务发生了异常：" + ex.getMessage());
            });
            return t;
        };
        ThreadPoolExecutor poolExecutor = ThreadPoolExecutorFactory.getPoolExecutor(threadFactory);
        for (int i = 0; i < 3; i++) {
            poolExecutor.execute(() -> {
                int res = 1 / 0;
                //int res = 1 / 1;
                System.out.println(Thread.currentThread().getName() + "线程-执行任务成功，结果：" + res);
            });
        }
        // 关闭线程池
        poolExecutor.shutdown();
    }

    public static int getCount() {
        return count.incrementAndGet();
    }
}
