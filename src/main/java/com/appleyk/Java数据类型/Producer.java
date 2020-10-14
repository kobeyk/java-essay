package com.appleyk.Java数据类型;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * <p></p>
 *
 * @author yukun24@126.com
 * @version V.0.1.1
 * @company 苏州中科蓝迪
 * @date created on 15:38 2020/10/13
 */
public class Producer implements Runnable{

    private BlockingQueue blockingQueue;

    public Producer(BlockingQueue queue){
        this.blockingQueue = queue;
    }

    @Override
    public void run() {
        while(true){
            try{
                int rand = new Random().nextInt(1000);
                System.out.println("生产了一个产品,产品号："+rand+"(3s后推送)");
                TimeUnit.SECONDS.sleep(3);
                this.blockingQueue.put(rand);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
