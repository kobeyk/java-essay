package com.appleyk.thread.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  8:17 下午 2020/12/6
 */
public class TheadLocalDemo {

    static  ThreadLocal<Map<String,String>> t1 = new ThreadLocal<Map<String,String>>(){
        @Override
        protected Map<String, String> initialValue() {
            // 当调用get方法时，如果当前线程的共享变量副本没有设置，就调用这个方法
            System.out.println("initialValue is called!");
            Map<String,String> resMap = new HashMap<>();
            resMap.put("name","Appleyk");
            return resMap;
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("我被调用了");
        }
    };

    //static final ThreadLocal<Map<String,String>> t2 = new ThreadLocal<>();

    public static void main(String[] args) {
        // 线程1获取
        new Thread(){
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
                System.out.println("当前线程："+Thread.currentThread().getName());
                Map<String,String> resMap = new HashMap<>();
                resMap.put("age","18");
                t1.set(resMap);
                System.out.println(t1.get());
                t1.remove();
            }
        }.start();

        // 线程2设置
        new Thread(){
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    System.out.println(e.getMessage());
                }
                System.out.println("当前线程："+Thread.currentThread().getName());
                t1.set(new HashMap<>());
            }
        }.start();
    }
}
