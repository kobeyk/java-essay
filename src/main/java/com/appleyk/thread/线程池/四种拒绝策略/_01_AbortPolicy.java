package com.appleyk.thread.线程池.四种拒绝策略;

import com.appleyk.thread.线程池.ThreadPoolExecutorFactory;
import com.appleyk.thread.线程池.tasks.MyTaskRunner01;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>越努力，越幸运</p>
 * 如果线程池太忙无法处理当前任务的话，就抛出异常，这个是线程池的默认拒绝执行策略
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  4:08 下午 2020/12/20
 */
public class _01_AbortPolicy {
    public static void main(String[] args) {

        /***
         * public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
         *   throw new RejectedExecutionException("Task " + r.toString() +
         *                     " rejected from " + e.toString());
         * }
         */

        ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        // 这里可以不用传，以证明是abortPolicy是默认的
        ThreadPoolExecutor poolExecutor = ThreadPoolExecutorFactory.getPoolExecutor();
        /* 由于，线程池的参数配置很低，所以，同时执行10个任务时，必定会超出线程池的承受能力
         * 一旦超出线程池的最大承受力的话，无法处理的任务必然会抛出RejectedExecutionException
         */
        for (int i = 0; i < 10; i++) {
            try {
                poolExecutor.submit(()->MyTaskRunner01.sayHi("submit"));
            }catch (Exception e){
                System.out.println("第{"+i+"}个任务被拒绝执行"+e.getMessage());
                // 拒绝了就手动处理异常，不能让当前任务一直挂起，阻塞
                continue;
            }
        }
        // 最后，关闭线程池
        poolExecutor.shutdown();
    }
}
