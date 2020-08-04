package com.appleyk.thread;

import java.util.concurrent.Semaphore;

/**
 * <p>越努力，越幸运 -- 信号量的使用</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class ThreadMain {

    // 开设一个餐厅，座位只有5个，模拟20位顾客排队等位置吃饭
    Semaphore semaphore;

    public ThreadMain(int res){
        semaphore = new Semaphore(res) ;
    }

    public void eat(int seconds) {
        try {
            // 等待叫号，如果叫到自己了，不用排队了，直接进去，由服务员告知位置，并且把餐位标记为有（permit-1）
            semaphore.acquire();
        }catch (Exception e){

        }
        System.out.println(Thread.currentThread().getName() + "就坐，准备吃饭了！");
        try {
            Thread.sleep(seconds);
        } catch (Exception e) {

        }
        // 吃完饭后，结账离开座位，这时候服务员把餐位标记为空（permit+1）
        semaphore.release();
        System.out.println(Thread.currentThread().getName() + ",结账，下一位！");
        System.out.println("====== ====== =======");
    }

    public static void main(String[] args) {
        ThreadMain app = new ThreadMain(5);
        for (int i = 0; i < 20; i++) {
            new Thread(()->app.eat(3000),"t"+(i+1)).start();
        }
    }
}
