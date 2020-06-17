package com.appleyk.thread.demo11;

import org.openjdk.jol.info.ClassLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class demo11 {
    public static void main(String[] args) {

        Object o = new Object();
        String[] arr = new String[]{"a","b"};
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println(ClassLayout.parseInstance(arr).toPrintable());
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        /**
         *  对象：
         *     对象头：
         *          A:Mark Word   -- 主要用来表示对象的线程锁状态，另外还可以用来配合GC、存放该对象的hashCode
         *          B:Klass Word --- 一个指向方法区中Class信息的指针，意味着该对象可随时知道自己是哪个Class的实例
         *          C:Array Length（可选）：数组长度也是占用64位（8字节）的空间，这是可选的，只有当本对象是一个数组对象时才会有这个部分；
         *     对象体：用于保存对象属性和值的主体部分，占用内存空间取决于对象的属性数量和类型；
         *     对齐字节：8字节8字节读取，比较方便，所以不够8整除的，补齐
         */
        Map<String,Object> map = new HashMap<>();
        if(map.isEmpty()){

        }
    }
}
