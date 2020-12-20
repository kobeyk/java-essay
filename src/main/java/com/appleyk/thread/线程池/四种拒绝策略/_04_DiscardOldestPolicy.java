package com.appleyk.thread.线程池.四种拒绝策略;

import com.appleyk.thread.线程池.ThreadPoolExecutorFactory;
import com.appleyk.thread.线程池.tasks.MyTaskRunner01;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>越努力，越幸运</p>
 * 如果线程池太忙（就是无法在new线程来处理当前任务）无法处理当前任务
 * 就把queue队列里最早进来的那个任务给干掉（由于队列是FIFO，先进先出的，最早入进来的Runnable任务
 * 有可能是执行时间最久的那个，因此，牺牲它（出列，poll()），保证最新的任务能够被处理，也是无奈之举啊）
 * ,然后当前任务在交由线程池进行处理
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  4:11 下午 2020/12/20
 */
public class _04_DiscardOldestPolicy {
    public static void main(String[] args) {
        /***
         *  public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
         *    if (!e.isShutdown()) {
         *         // 从任务队列里出列一个最先入队的
         *         e.getQueue().poll();
         *         // 然后在执行当然任务
         *         e.execute(r);
         *     }
         *  }
         */
        ThreadPoolExecutor.DiscardOldestPolicy discardOldestPolicy = new ThreadPoolExecutor.DiscardOldestPolicy();
        ThreadPoolExecutor poolExecutor = ThreadPoolExecutorFactory.getPoolExecutor(discardOldestPolicy);
        /* 由于，线程池的参数配置很低，所以，同时执行10个任务时，必定会超出线程池的承受能力
         * 一旦超出线程池的最大承受力的话，无法处理的任务必然会抛出RejectedExecutionException
         */
        int i  =10;
        while(i>0){
            final int seq = i;
            // 执行结果，你会发现，原本10个任务，实际上真正执行的却不到10个
            // 为什么呢，因为当旧任务正准备被线程执行时，新任务来了，发现当前无法再创建新的线程
            // 那怎么办，就把就的任务给干掉，结果就导致老任务被从任务队列里移除，而新的任务得以继续执行
            poolExecutor.submit(() -> System.out.println("当前是第"+seq+"个任务，"+
                    MyTaskRunner01.sayHiWithReturn("submit")));
            i--;
        }
        // 最后，关闭线程池
        poolExecutor.shutdown();
    }
}
