package com.appleyk.thread.线程池.当线程执行异常会影响其他线程吗;

import com.appleyk.thread.线程池.ThreadPoolConfig;
import com.appleyk.thread.线程池.ThreadPoolExecutorFactory;
import com.appleyk.thread.线程池.tasks.MyTaskRunner01;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  8:41 下午 2020/12/20
 */
public class ThreadPoolTaskExecutorTest {

    public static void main(String[] args) {
        // 先把线程池的配置放宽一些,不然线程池里面最大就两个线程跑的话，看不出来效果
        ThreadPoolConfig.corePoolSize = 5;
        ThreadPoolConfig.maximumPoolSize = 10;
        ThreadPoolTaskExecutor poolTaskExecutor = ThreadPoolExecutorFactory.getPoolTaskExecutor("嘻嘻 - ");
        // 执行5个任务，其中4个正常，一个异常，如果异常的线程会影响其他任务的执行的话，输出结果很明显不会有5条
        // 反之，只要出现5条任务执行记录，就可以表明当线程池中的某一个任务执行异常的话，是不会影响到其他任务的正常执行的
        // 为什么呢？ 原理很简单，就是线程池会将执行异常的线程从池子里给remove掉,然后再add一个新的woker
        poolTaskExecutor.execute(()-> MyTaskRunner01.sayHi("submit"));
        poolTaskExecutor.execute(()-> MyTaskRunner01.sayHi("submit"));
        poolTaskExecutor.execute(()-> MyTaskRunner01.sayHiWithExp("submit"));
        poolTaskExecutor.execute(()-> MyTaskRunner01.sayHi("submit"));
        poolTaskExecutor.execute(()-> MyTaskRunner01.sayHi("submit"));
        // 关闭线程池
        poolTaskExecutor.shutdown();
    }



}
