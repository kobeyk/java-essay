package com.appleyk.thread.线程池;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * <p>越努力，越幸运</p>
 * 线程池对象创建工厂，每次都会创建一个新的线程池对象
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  4:16 下午 2020/12/20
 */
public class ThreadPoolExecutorFactory {

    public static ThreadPoolExecutor getPoolExecutor(RejectedExecutionHandler handler){
       return new ThreadPoolExecutor(ThreadPoolConfig.corePoolSize,
                ThreadPoolConfig.maximumPoolSize,
                ThreadPoolConfig.keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(ThreadPoolConfig.queueCapacity),
                handler);
    }

    public static ThreadPoolExecutor getPoolExecutor(){
        return new ThreadPoolExecutor(ThreadPoolConfig.corePoolSize,
                ThreadPoolConfig.maximumPoolSize,
                ThreadPoolConfig.keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(ThreadPoolConfig.queueCapacity));
    }

    public static ThreadPoolExecutor getPoolExecutor(ThreadFactory factory){
        return new ThreadPoolExecutor(ThreadPoolConfig.corePoolSize,
                ThreadPoolConfig.maximumPoolSize,
                ThreadPoolConfig.keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(ThreadPoolConfig.queueCapacity),
                factory);
    }

    public static ThreadPoolTaskExecutor getPoolTaskExecutor(String prefix){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setThreadNamePrefix(prefix);
        taskExecutor.setCorePoolSize(ThreadPoolConfig.corePoolSize);
        taskExecutor.setMaxPoolSize(ThreadPoolConfig.maximumPoolSize);
        taskExecutor.setKeepAliveSeconds((int)ThreadPoolConfig.keepAliveTime);
        taskExecutor.setQueueCapacity(ThreadPoolConfig.queueCapacity);
        // 默认就是这个拒绝策略，这里只是为了说明，可以设置任务拒绝执行策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 初始化内置ThreadPoolExecutor对象,如果不调用下面的方法，那上面设置的一些参数就等于白设置了，不信可以看源码
        taskExecutor.initialize();
        return taskExecutor;
    }
}
