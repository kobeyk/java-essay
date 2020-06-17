package com.appleyk.thread.dcl;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class demoVolatile {

    private int count = 0;

    public void add(){
        //try {
        //    Thread.sleep(1);
        //    // 睡1毫秒，模拟业务处理
        //}catch (InterruptedException e){
        //
        //}
        System.out.println("线程"+Thread.currentThread().getName()+"读到count = "+count);
        // 上面业务处理完了，执行真正的操作，给count+1
        count++;
        System.out.println("线程"+Thread.currentThread().getName()+"执行完了！");

    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        demoVolatile demoVolatile = new demoVolatile();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i <1000; i++) {
           threads.add( new Thread(()->demoVolatile.add(),"t"+(i+1)));
        }
        threads.forEach(t -> t.start());
        threads.forEach(t->{
            try {
                t.join();
            }catch (InterruptedException e){}
        });
        System.out.println("count = " + demoVolatile.getCount());
    }

}
