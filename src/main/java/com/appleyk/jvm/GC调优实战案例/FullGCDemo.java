package com.appleyk.jvm.GC调优实战案例;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:30 下午 2020/10/26
 */
public class FullGCDemo {

    public static class CardInfo{
        BigDecimal price = new BigDecimal(0.0);
        private String name = "appleyk";
        private int age = 30;
        private Date birth = new Date();

        public void m(){
            // do something
        }
    }

    // 定义一个定时任务线程池
    private static ScheduledThreadPoolExecutor executor  = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws Exception{
        executor.setMaximumPoolSize(50);
        for(;;){
            execute();
            Thread.sleep(100);
        }

    }

    private static void execute(){
        List<CardInfo> tasks = getCards();
        // 循环遍历模型，每一个模型对应一个业务(任务)，这里从线程池拿线程定时去执行
        tasks.forEach(task->{
            executor.scheduleWithFixedDelay(()->{
                task.m();
            },2,3, TimeUnit.SECONDS);
        });
    }

    private static List<CardInfo> getCards(){
        // 模拟数据库查询，一次取100个
        List<CardInfo> cards = new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            CardInfo cardInfo = new CardInfo();
            cards.add(cardInfo);
        }
        return cards;
    }

}
