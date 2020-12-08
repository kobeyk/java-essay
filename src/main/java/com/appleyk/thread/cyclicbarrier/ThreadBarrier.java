package com.appleyk.thread.cyclicbarrier;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p></p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 17:21 2020/12/8
 */
public class ThreadBarrier {

    // 设置次屏障的参与者条目为5,当所有人达到屏障后，执行线程
    // 当所有人到达屏障后，屏障将会被重置，又可以继续玩耍了
    static CyclicBarrier barrier = new CyclicBarrier(5,
            ()->{
                System.out.println("裁判：各就各位，预备.....");
            });

    static class TaskRunner implements Runnable{

        private CyclicBarrier barrier;
        public TaskRunner(CyclicBarrier barrier) {
            this.barrier = barrier;
        }
        @Override
        public void run() {
            try{
                int sleepMills = ThreadLocalRandom.current().nextInt(3000);
                Thread.sleep(sleepMills);
                System.out.println(Thread.currentThread().getName() + "选手已就位, 准备共用时： " + sleepMills + "ms");
                barrier.await();
            }catch (InterruptedException  | BrokenBarrierException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * ThreadPoolExecutor(int corePoolSize, int maximumPoolSize,
     *                        long keepAliveTime, TimeUnit unit,
     *                        BlockingQueue<Runnable> workQueue,
     *                        RejectedExecutionHandler handler)
     *  corePoolSize 线程池维护线程的最少数量
     *  maximumPoolSize 线程池维护线程的最大数量
     *  keepAliveTime 线程池维护线程所允许的空闲时间
     *  unit 时间单位
     *  workQueue 线程池所使用的缓冲队列
     *  handler 线程池对拒绝任务的处理策略
     */

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        int size = 5;
        AtomicInteger counter = new AtomicInteger();
        // 注意第四个参数是一个ThreadFactory接口类型，该接口就一个方法newThread(Runnable r),所以可以直接用lambda替代
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(size, size,
                1000,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                (r) -> new Thread(r, counter.addAndGet(1) + "号·"),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i <10 ; i++) {
            threadPool.submit(new TaskRunner(barrier));
        }
        // 关闭线程池
        threadPool.shutdown();
    }
}
