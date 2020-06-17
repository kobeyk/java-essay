package com.appleyk.thread.dcl;

/**
 * <p>越努力，越幸运 -- DCL(Double check lock, 双重检查锁)</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class SingletonInstance {
    private static volatile SingletonInstance instance;

    public static SingletonInstance getInstance1(){
        return new SingletonInstance();
    }

    public static synchronized SingletonInstance getInstance2(){
        if(instance == null){
            instance = new  SingletonInstance();
        }
        return instance;
    }

    public static SingletonInstance getInstance3(){
        if(instance == null){
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){

            }
            instance = new  SingletonInstance();
        }
        return instance;
    }

    // 双重检查锁，DCL单例
    public static SingletonInstance getInstance4(){
        if(instance == null){
            synchronized (SingletonInstance.class){
                if(instance == null){
                    try {
                        Thread.sleep(10);
                    }catch (InterruptedException e){

                    }
                    instance = new SingletonInstance();
                }
                return instance;
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i <100 ; i++) {
            new Thread(()->{
                System.out.println(SingletonInstance.getInstance4().hashCode());
            }).start();
        }
        long end = System.currentTimeMillis();
        System.out.println("执行耗时："+(end-start));
    }

}
