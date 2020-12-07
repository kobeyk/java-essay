package com.appleyk.leetcode;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  11:15 下午 2020/11/5
 */
public class ZeroEvenOdd {

    private int n;

    private Semaphore zero = new Semaphore(0);
    private Semaphore even = new Semaphore(0);
    private Semaphore odd  = new Semaphore(0);

    // zero信号量对象，通过下面这个标记，来判断是是否奇数的permit还是偶数的permit
    private volatile boolean isOdd = true;

    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 0; i <n ; i++) {
            printNumber.accept(0);
            if(isOdd){
                // 如果当前是奇数，那就是否奇数的permit
                odd.release();
            }else{
                even.release();
            }
            zero.acquire();//阻塞，等到下一个数执行后，拿到释放的zero许可，继续输出0
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        if(n<2){
            return;
        }
        for (int i = 2; i <=n ; i++) {
            if((i&1) ==0){
                even.acquire(); // 如果是偶数的话，那就拿偶数锁
                printNumber.accept(i);
                isOdd=true;// 如果顺利拿到了，那就把isOdd状态改为true，意味着，下一个该输出奇数了
                zero.release();// 输出完，立刻释放zero的许可
            }
        }

    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        if(n<1){
            return;
        }
        for (int i = 1; i <=n ; i++) {
            if((i&1) == 1){
                odd.acquire(); // 如果是偶数的话，那就拿奇数锁
                printNumber.accept(i);
                isOdd = false; // 如果顺利拿到了，那就把isOdd状态改为false，意味着，下一个该输出偶数了
                zero.release();// 输出完，立刻释放zero的许可
            }
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(1);
        new Thread(()-> {
            try {
                zeroEvenOdd.odd(i-> System.out.print(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()-> {
            try {
                zeroEvenOdd.zero(i-> System.out.print(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()-> {
            try {
                zeroEvenOdd.even(i-> System.out.print(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
