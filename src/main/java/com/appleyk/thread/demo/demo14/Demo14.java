package com.appleyk.thread.demo.demo14;

import java.util.Hashtable;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public  class Demo14 implements Runnable {

    public Demo14() {
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("线程被中断，程序退出！");
                return;
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("线程休眠过程中被中断，程序退出！");
                // 这个异常会清除中断标记，所以上面那个判断是走不通的，也就是程序会继续
                // 如果想要程序收到中断的信号，正确退出程序的话的，需要人为的再标记上中断状态
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        //Thread thread = new Thread(new Demo14());
        //thread.start();
        //Thread.sleep(1000);
        //thread.interrupt();
        //System.out.println("hi");
        System.out.println(Math.round(-1.5));
        Hashtable<String,Long> res = new Hashtable<>();
        res.put(null,1L);
    }
}
