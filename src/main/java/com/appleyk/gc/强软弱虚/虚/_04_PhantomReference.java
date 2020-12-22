package com.appleyk.gc.强软弱虚.虚;

import com.appleyk.gc.强软弱虚.M;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>越努力，越幸运</p>
 * 虚引用，应用场景：nio，零拷贝，堆外内存管理
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:34 下午 2020/12/22
 */
public class _04_PhantomReference {

    static List<Object> COLLECTS = new ArrayList<>();
    static ReferenceQueue<M> QUEUE = new ReferenceQueue<>();
    static volatile boolean STOP = false;
    public static void main(String[] args) {
        PhantomReference<M> m = new PhantomReference<>(new M(), QUEUE);
        // 一个线程不停的往list中放byte数组，也就是不断申请堆空间
        new Thread(() -> {
            while (!STOP) {
                COLLECTS.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 虚引用不管你怎么取，都是访问不到的
                System.out.println(m.get());
            }
        }).start();
        // 一个线程，不断的监听队列，如果队列里有对象，说明该对象被回收了
        new Thread(() -> {
            while (true) {
                // 只有当发生gc时，虚对象才会被回收，然后放到队里里
                Reference<? extends M> refObj = QUEUE.poll();
                if (refObj != null) {
                    System.out.println("--- 虚引用对象被jvm回收了！接下来，你可以做一些事情了 ---"+refObj);
                    STOP = true;
                    break;
                }
            }
        }).start();

        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        // jvm堆上直接开辟1M的空间
        ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
        // jvm堆外（内存）上直接开辟1M的空间
        ByteBuffer bufferDirectly = ByteBuffer.allocateDirect(1024*1024);
    }
}
