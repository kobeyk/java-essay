package com.appleyk.thread.condition;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>基于AQS框架代码层面的锁（条件）测试</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 14:54 2020/12/8
 */
public class TaskQueue {

    // 默认非公平锁
    final ReentrantLock lock = new ReentrantLock();
    // 条件对象
    final Condition condition = lock.newCondition();
    // 队列
    Queue<String> queue;

    public TaskQueue() {
        this.queue = new LinkedList<>();
    }

    public void addTask(String value){
        System.out.println("add element wait...");
        lock.lock();
        try{
            queue.add(value);
            // 调用signal方法唤醒对应条件队列中等待时间最久的线程并加入到等待队列中，使得当前节点重新获得抢占锁的机会
            condition.signalAll();
            System.out.println(" ===== 唤醒条件队列里所有的线程，让他们重新进入等待队列... ====");
        }finally {
            lock.unlock();
        }
    }

    public String getTask(){
        System.out.println("get element wait...");
        lock.lock();
        try{
            while(queue.isEmpty()){
                // 如果队列空的话，当前线程就进入条件队列，同时释放已经获得的锁
                condition.await();
            }
            return queue.remove();
        }catch (InterruptedException e){
            return "";
        }finally {
            lock.unlock();
        }
    }

    static class TaskConsumer implements Runnable{
        TaskQueue taskQueue;
        public TaskConsumer(TaskQueue taskQueue) {
            this.taskQueue = taskQueue;
        }

        @Override
        public void run() {
            while (true){
                System.out.println("线程："+Thread.currentThread().getName()+",消费【"+taskQueue.getTask()+"】");
            }
        }
    }

    static class TaskProducer implements Runnable{
        TaskQueue taskQueue;
        public TaskProducer(TaskQueue taskQueue) {
            this.taskQueue = taskQueue;
        }
        @Override
        public void run() {
            while (true){
                String p = String.valueOf(System.currentTimeMillis());
                System.out.println("线程："+Thread.currentThread().getName()+",生产【"+p+"】");
                taskQueue.addTask(p);
                try {
                    Thread.sleep(5000);
                }catch (InterruptedException e){

                }
            }
        }
    }

    public static void main(String[] args) {
       TaskQueue taskQueue = new TaskQueue();
       new Thread(new TaskConsumer(taskQueue)).start();
       new Thread(new TaskProducer(taskQueue)).start();
    }

}

