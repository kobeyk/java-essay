package com.appleyk.thread.demo2;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Demo2 {

    // volatile（保证了共享变量的"可见性"） 线程可见，当值改的时候，会强刷，于是所有线程就看到了新值
    // volatile只保证了可见性，但它不是线程安全的
    //volatile boolean running = true;
    volatile boolean running = true;

     int count = 10;

    public void test(){
        System.out.println(Thread.currentThread().getName()+" start");
        while (running){
            // 输出这个后，当cpu反应过来时，线程就看到了新值
            System.out.println("66666666");
        }
        System.out.println(Thread.currentThread().getName()+" over");
    }

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        new Thread(()->demo2.test(),"t1").start();
        //new Thread(demo2::test,"t1").start();
        try {
            Thread.sleep(1000);
        }catch (Exception e){
        }
        demo2.running = false;
    }

}
