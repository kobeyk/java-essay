package com.appleyk.thread.countdownlatch;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>利用锁栓+线程池来实现</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 16:42 2020/12/8
 */
public class ThreadLatch {

    static CountDownLatch latch = new CountDownLatch(5);

    static class TaskRunner implements Runnable{
        private CountDownLatch latch;
        public TaskRunner(CountDownLatch latch) {
            this.latch = latch;
        }
        @Override
        public void run() {
            try{
                int sleepMills = ThreadLocalRandom.current().nextInt(3000);
                Thread.sleep(sleepMills);
                System.out.println(Thread.currentThread().getName() + "选手已就位, 准备共用时： " + sleepMills + "ms");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                latch.countDown();
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


    public static void main(String[] args) throws InterruptedException{
        int size = 5;
        AtomicInteger counter = new AtomicInteger();
        // 注意第四个参数是一个ThreadFactory接口类型，该接口就一个方法newThread(Runnable r),所以可以直接用lambda替代
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(size, size,
                1000,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                (r) -> new Thread(r, counter.addAndGet(1) + "号·"),
                new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i <size ; i++) {
          threadPool.submit(new TaskRunner(latch));
        }
        latch.await();
        System.out.println("裁判：各就各位，预备.....");
        // 关闭线程池
        threadPool.shutdown();
    }
}
