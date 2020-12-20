package com.appleyk.thread.线程池.四种拒绝策略;

import com.appleyk.thread.线程池.ThreadPoolExecutorFactory;
import com.appleyk.thread.线程池.tasks.MyTaskRunner01;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * <p>越努力，越幸运</p>
 * 如果线程池忙不过来处理当前的任务，就交给当前主线程来完成
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  4:08 下午 2020/12/20
 */
public class _02_CallerRunsPolicy {
    public static void main(String[] args) {
        /***
         * public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
         *   if (!e.isShutdown()) {
         *      r.run();
         *   }
         * }
         */
        ThreadPoolExecutor.CallerRunsPolicy callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor poolExecutor = ThreadPoolExecutorFactory.getPoolExecutor(callerRunsPolicy);
        /* 由于，线程池的参数配置很低，所以，同时执行10个任务时，必定会超出线程池的承受能力
         * 一旦超出线程池的最大承受力的话，无法处理的任务必然会抛出RejectedExecutionException
         */
        int i =0;
        while (i<10){
            // 这里执行的时候，你会发现，会参杂着主线程来执行当前任务
            poolExecutor.submit(() -> MyTaskRunner01.sayHi("submit"));
            i++;
        }
        // 最后，关闭线程池
        poolExecutor.shutdown();
    }
}
