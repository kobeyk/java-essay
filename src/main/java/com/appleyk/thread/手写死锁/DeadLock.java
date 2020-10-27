package com.appleyk.thread.手写死锁;

/**
 * <p>手写死锁</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 * @date created on 10:28 2020/10/27
 */
public class DeadLock {

    public static void main(String[] args) {
        Thread t1 = new Thread(()->new LockThread(true).run());
        Thread t2 = new Thread(()->new LockThread(false).run());
        t1.start();
        t2.start();
    }

}

class LockThread implements Runnable{

    private boolean bFlag = false;

    private static Object obj1 = new Object();
    private static Object obj2 = new Object();

    public LockThread(boolean bFlag){
        this.bFlag = bFlag;
    }
    @Override
    public void run(){
        if(bFlag){
            System.out.println(Thread.currentThread().getName()+"--我准备抢占资源");
            synchronized (obj1){
                System.out.println(Thread.currentThread().getName()+"--我拿到了对象1的锁");
                System.out.println("执行业务A...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj2){
                    System.out.println(Thread.currentThread().getName()+"--我拿到了对象2的锁");
                    System.out.println("执行业务B...");
                }
            }
        }else{
            synchronized (obj2){
                System.out.println(Thread.currentThread().getName()+"--我拿到了对象2的锁");
                System.out.println("执行业务A...");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (obj1){
                    System.out.println(Thread.currentThread().getName()+"--我拿到了对象1的锁");
                    System.out.println("执行业务B...");
                }
            }
        }

    }
}



