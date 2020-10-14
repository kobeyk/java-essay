package com.appleyk.Java数据类型;

import java.util.concurrent.BlockingQueue;

/**
 * <p></p>
 *
 * @author yukun24@126.com
 * @version V.0.1.1
 * @company 苏州中科蓝迪
 * @date created on 15:38 2020/10/13
 */
public class Consumer implements Runnable{

    private BlockingQueue blockingQueue;

    public Consumer(BlockingQueue queue){
        this.blockingQueue = queue;
    }

    @Override
    public void run() {
        while(true){
            try{
                System.out.println("消费了一个产品,产品号："+this.blockingQueue.take());
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
