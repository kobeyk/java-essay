package com.appleyk.gc.强软弱虚.弱;


import java.lang.ref.WeakReference;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:29 下午 2020/12/22
 */
public class _03_WeakReference {
    public static void main(String[] args) {
        WeakReference<byte[]> m = new WeakReference<>(new byte[1024*1024*10]);
        System.out.println(m.get());
        System.gc();
        // 只要发生GC，弱引用指向的堆空间就会被回收
        System.out.println(m.get());

        //ThreadLocal<M> tl = new ThreadLocal<>();
        //tl.set(new M());
        //// 养成好的习惯，手动把当前threadlocal（key）的entry个移除了
        //tl.remove();
    }
}
