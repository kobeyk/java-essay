package com.appleyk.thread.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * <p>利用ForkJoin框架，计算连续自然数的和</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  22:24 2020/9/6
 */
public class MyForkTask extends RecursiveTask<Integer> {

    /**任务拆分的阈值*/
    private static final int threshold = 100;
    /**起始数*/
    private int start;
    /**末尾数*/
    private int end;

    public MyForkTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**计算*/
    @Override
    protected Integer compute() {
        int sum = 0 ;
        if(end-start<=threshold){
            /**直接计算*/
            for (int i = start; i <= end ; i++) {
                sum += i;
            }
        }else{
            /**否则继续拆分*/
            int mid = (end+start)/2;
            MyForkTask left = new MyForkTask(start,mid);
            MyForkTask right = new MyForkTask(mid+1,end);
            invokeAll(left,right);
            sum = left.join()+right.join();
        }
        return sum;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool(
                Runtime.getRuntime().availableProcessors()*10
        );
        ForkJoinTask<Integer> forkTask = new MyForkTask(1, 100100);
        Integer sum = forkJoinPool.invoke(forkTask);
        System.out.println("最终结果："+sum+",耗时："+(System.currentTimeMillis()-start)+"ms");
    }
}
