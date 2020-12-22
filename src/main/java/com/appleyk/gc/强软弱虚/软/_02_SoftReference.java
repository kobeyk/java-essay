package com.appleyk.gc.强软弱虚.软;


import java.io.IOException;
import java.lang.ref.SoftReference;

/**
 * <p>越努力，越幸运</p>
 * 应用场景，位图加载到内存中作为缓存使用，当内存够的时候，就缓存起来，当内存不够时，就gc掉
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:14 下午 2020/12/22
 */
public class _02_SoftReference {
    public static void main(String[] args) throws IOException {
        // 10M,其中m是强引用，指向了一个SoftReference对象引用了一个软引用的内存空间
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);
        System.out.println(m.get());//必须有值
        System.gc();
        try{
            Thread.sleep(500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        // 第一次gc的时候，不会回收，因为堆空间够
        System.out.println(m.get());// 不null
        // 再次new一个12M的数组，这样堆空间最大20M就不够用了，只能干掉软引用的堆空间了
        byte[] b = new byte[1024*1024*12];
        System.out.println(m.get());// null
    }

}
