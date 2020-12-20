package com.appleyk.thread.线程池.四种拒绝策略;

import com.appleyk.thread.线程池.ThreadPoolExecutorFactory;
import com.appleyk.thread.线程池.tasks.MyTaskRunner01;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>越努力，越幸运</p>
 * 如果线程池太忙无法处理当前任务，就直接抛弃任务，不抛任何异常
 * 也就是好像什么都没有发生一样，如果采用此策略，最好执行的是那些无关痛痒的任务
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  4:09 下午 2020/12/20
 */
public class _03_DiscardPolicy {
    public static void main(String[] args) {
        /***
         * public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
         *   if (!e.isShutdown()) {
         *      r.run();
         *   }
         * }
         */
        ThreadPoolExecutor.DiscardPolicy discardPolicy = new ThreadPoolExecutor.DiscardPolicy();
        ThreadPoolExecutor poolExecutor = ThreadPoolExecutorFactory.getPoolExecutor(discardPolicy);
        /* 由于，线程池的参数配置很低，所以，同时执行10个任务时，必定会超出线程池的承受能力
         * 一旦超出线程池的最大承受力的话，无法处理的任务必然会抛出RejectedExecutionException
         */
        int i = 0;
        do{
            final int seq = i;
            // 执行结果，你会发现，有一些任务是被无情的抛弃了，一点痕迹都没有留下
            poolExecutor.submit(() -> System.out.println("当前是第"+seq+"个任务，"+
                    MyTaskRunner01.sayHiWithReturn("submit")));
            i++;
        }while (i<10);

        // 最后，关闭线程池
        poolExecutor.shutdown();
    }
}
