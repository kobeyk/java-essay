package com.appleyk.gc;

/**
 * <p>代码证明JVM判断对象是否存活用的并不是引用计数算法</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:34 下午 2020/9/1
 */
public class GcTest02 {
    private Object instance = null;
    private static final int _INT_1MB = 1024*1024;
    private byte[] bigBytes = new byte[2*_INT_1MB];

    public static void main(String[] args) {
        GcTest02 c1 = new GcTest02();
        GcTest02 c2 = new GcTest02();
        c1.instance = c2;
        c2.instance = c1;
        c1 = null;
        c2 = null;
        // 注意，打印GC日志信息，需要再run的启动配置里的 VM options 加上 -XX:+PrintGCDetails
        System.gc();
    }
}
