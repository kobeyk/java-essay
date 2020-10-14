package com.appleyk.Java数据类型;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p></p>
 *
 * @author yukun24@126.com
 * @version V.0.1.1
 * @company 苏州中科蓝迪
 * @date created on 14:55 2020/10/13
 */
public class CollectionsDemo {
    public static void main(String[] args)throws Exception {
////        List<Integer> ages = Collections.synchronizedList(new ArrayList<>());
////        ages.add(1);
////        System.out.println(ages);
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
        System.out.print(queue.offer(1) + " ");
        System.out.print(queue.offer(2) + " ");
        System.out.print(queue.offer(3) + " ");
        System.out.print(queue.take() + " ");
        System.out.println(queue.size());
//        BlockingQueue<Integer> queue = new SynchronousQueue<>();
//        new Thread(new Producer(queue)).start();
//        new Thread( new Consumer(queue)).start();
    }
}
