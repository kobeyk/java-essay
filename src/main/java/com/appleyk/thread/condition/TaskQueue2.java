package com.appleyk.thread.condition;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>基于JVM层面上的（指令集）Synchronized隐式锁进行测试</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 14:54 2020/12/8
 */
public class TaskQueue2 {

    final Object lock = new Object();
    // 队列
    Queue<String> queue;

    public TaskQueue2() {
        this.queue = new LinkedList<>();
    }

    public void addTask(String value){
        System.out.println("add element wait...");
        synchronized (lock){
            queue.add(value);
            // 通知所有等待中的线程
            lock.notifyAll();
            try{
                Thread.sleep(100);
            }catch (InterruptedException e){
            }
            System.out.println(" ===== 唤醒条件队列里所有的线程，让他们重新进入等待队列... ====");
        }
    }

    public String getTask(){
        System.out.println("get element wait...");
        synchronized (lock){
            while(queue.isEmpty()){
                // 如果队列空的话，就阻塞等待，知道被唤醒
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return queue.remove();
        }
    }

    static class TaskConsumer implements Runnable{
        TaskQueue2 taskQueue;
        public TaskConsumer(TaskQueue2 taskQueue) {
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
        TaskQueue2 taskQueue;
        public TaskProducer(TaskQueue2 taskQueue) {
            this.taskQueue = taskQueue;
        }
        @Override
        public void run() {
            while (true){
                String p = String.valueOf(System.currentTimeMillis());
                System.out.println("线程："+Thread.currentThread().getName()+",生产【"+p+"】");
                taskQueue.addTask(p);
                try {
                    Thread.sleep(3000);
                }catch (InterruptedException e){

                }
            }
        }
    }

    public static void main(String[] args) {
        TaskQueue2 taskQueue = new TaskQueue2();
        new Thread(new TaskConsumer(taskQueue)).start();
        new Thread(new TaskProducer(taskQueue)).start();

    }

}

