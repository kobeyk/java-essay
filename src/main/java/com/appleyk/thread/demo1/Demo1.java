package com.appleyk.thread.demo1;

import java.util.concurrent.TimeUnit;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Demo1 {

    private int count = 6 ;

    synchronized void test(){
        System.out.println(Thread.currentThread().getName()+" start");
        while (count>0){
            count--;
            System.out.println(Thread.currentThread().getName()+" count= "+count);
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                System.out.println(e);
            }
            if(count == 5){
                try{
                    // 处理下异常，这样的话，对象的锁就不会被释放，t2就永远也别想拿到锁
                    // 如果异常不处理，直接抛异常的话，t1拿到的对象的锁就会被释放，然后t2开始执行
                    System.out.println(count/0);
                }catch (Exception e){

                }
            }
        }
        System.out.println(Thread.currentThread().getName()+" over");
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        Runnable r = ()->demo1.test();
        new Thread(r,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            System.out.println(e);
        }
        new Thread(r,"t2").start();
    }

}
