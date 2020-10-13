package com.appleyk.gc;

import java.util.concurrent.RecursiveTask;

/**
 * <p>利用jConsole工具，监控java程序在jvm运行时数据区的内存和线程状况</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  14:15 2020/9/6
 */
public class GcTest04 implements Runnable{

    public int a,b;

    public GcTest04(int a , int b){
        this.a=a;
        this.b=b;
    }

    @Override
    public void run() {
        // 下面这个会造成死锁，因为Integer.value(int)方法为了减少创建对象和节省内存空间
        // 会将【-128,127】之间的整数缓存起来，因为一般人用int的话，范围会比较小
        // 缓存范围大了浪费内存空间，因此，综合了下，jdk缓了这个范围内的
        // 那么问题就来了，一旦多线程下，两个线程，假设a和b的值正好相反
        // 比如，线程1的 a=1，b=2，而线程2的 a=2，b=1,
        // 这样就会造成，线程1先获得了对象1的锁，要想获取2的锁需要线程2释放
        // 但是，线程2先获得了2的锁，要想获取1的锁，得等到线程1释放
        // 于是乎，二者就互相等待对方释放自己需要的资源对象的锁
        // 结果就是，谁也不释放，就那么干等着，就好比，路只有一条单向，两辆车正好头对头
        // 如果双方均不妥协，有一个后退让另一个先通行的话，那两辆车只能干耗着
        // 就看谁带的干粮多了
        synchronized (Integer.valueOf(a)){
            synchronized (Integer.valueOf(b)){
                System.out.println(a+b);
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <100 ; i++) {
            new Thread(new GcTest04(1,2)).start();
            new Thread(new GcTest04(2,1)).start();
        }
    }
}
