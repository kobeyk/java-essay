package com.appleyk.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class AQSMain {

    static boolean flag = true;
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        for (int i = 0; i <10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int count = 3;
                    while(count-- > 0){
                        lock.lock();
                        if(!flag){
                            System.out.println(Thread.currentThread().getName()+"--我去，资源被锁了，我还是退下吧");
                            lock.unlock();
                            break;
                        }
                        flag = false;
                        System.out.println(Thread.currentThread().getName()+" -- 我拿到了锁");
                        // 释放锁
                        lock.unlock();
                        System.out.println(Thread.currentThread().getName()+" -- 我释放了锁");
                        flag = true;
                    }
                }
            }).start();
        }
    }
}
