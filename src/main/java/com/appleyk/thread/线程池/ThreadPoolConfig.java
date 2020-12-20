package com.appleyk.thread.线程池;

/**
 * <p>越努力，越幸运</p>
 * 线程池配置类
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  4:17 下午 2020/12/20
 */
public class ThreadPoolConfig {
    // 核心线程数
    public static int corePoolSize = 1;
    // 最大线程数
    public static int maximumPoolSize = 2;
    // 当前线程生存时间
    public static long keepAliveTime = 30;
    // 线程池队列,顺序的、阻塞有界队列（主要用与生产者和消费者场景）
    public static int queueCapacity = 3;
}
