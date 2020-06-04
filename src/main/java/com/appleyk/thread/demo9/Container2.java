package com.appleyk.thread.demo9;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>面试题：实现一个容器，提供两个方法：add、size
 * 写两个线程，一个线程往里面add10个数，一个线程监听，如果当前个数达到5时，线程2结束并给出提示
 * </p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Container2 {

    volatile List<Integer> cols = new ArrayList<>();
    public void add(int i){
        cols.add(i);
        System.out.println(Thread.currentThread().getName()+" add i = "+i);
    }
    public Integer size(){
        return cols.size();
    }

    public static void main(String[] args) {
        Container2 c1  = new Container2();
        new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                c1.add(i);
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){}
            }
        },"t1").start();
        new Thread(()->{
            while(true){
                if(c1.size() == 5){
                    break;
                }
            }
            System.out.println("=====t2线程结束！======");
        },"t2").start();
    }
}
