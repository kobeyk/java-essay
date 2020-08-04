package com.appleyk.thread.demo13;

import java.util.concurrent.Semaphore;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class AnotherDummy {

    /**初始化两个许可=0的两个信号量（只有permit=1，线程才不会阻塞）*/
    private Semaphore second = new Semaphore(0);
    private Semaphore third = new Semaphore(0);

    public void one(Runnable runnable) throws InterruptedException {
        runnable.run();
        // 先释放一个,permit+1，否则two方法不会执行
        second.release();
    }

    public void two(Runnable runnable) throws InterruptedException {
        // 只有permit = 1 ，才可以获取许可，然后two方法才可以解除阻塞继续执行
        second.acquire();
        runnable.run();
        // 让第三个方法的信号量的permit+1
        third.release();
    }

    public void three(Runnable runnable) throws InterruptedException {
        third.acquire();
        runnable.run();
    }
}
