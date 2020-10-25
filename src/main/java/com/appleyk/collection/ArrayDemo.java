package com.appleyk.collection;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:06 下午 2020/10/12
 */
public class ArrayDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        /**Array是反射包中的一个final类，无法被继承，且构造器是private，外部无法new实例化*/
        /**只能通过newInstance来创建数据，其是一个静态方法，调用的是本地方法，通过调用C++语言进行内存申请*/
        /**newInstance方法，可以创建一维数组，也可以创建多维数据*/
        /**为什么不提供public构造器呢，因为数组的类型太多，*/
        Object array = Array.newInstance(int.class, 5);
        Array.set(array,1,1);
        Array.set(array,2,2);
        int[] a = new int[]{1,2};
        System.out.println(Array.get(array,2));
        Integer m=128,n=128;
        System.out.println(m == n);
        Integer x = 127,y=127;
        System.out.println(x == y);

        // 反射Integer类
        Class<?> cacheClz = Integer.class.getDeclaredClasses()[0];
        // 拿到成员变量
        Field cache = cacheClz.getDeclaredField("cache");
        // 设置成cache字段可以被修改
        cache.setAccessible(true);
        // 拿到这个IntegerCache对象里面的cache数组
        Integer[] newCaches =(Integer[]) cache.get("cache");
        System.out.printf("0 = %d,1 = %d, 4 = %d",newCaches[128],newCaches[128+1]
                ,newCaches[128+4]);
        System.out.println();
        // 篡改了integer的缓存，将4改成5
        newCaches[132] =newCaches[133];
        int b = 2;
        int c = b+b;
        // 所以这个玩意，最终输出结果是 2+2 = 5
        System.out.printf("%d + %d = %d",b,b,c);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        // new DirectByteBuffer(capacity);
        // Cleaner 继承了虚引用类
        // public class Cleaner extends PhantomReference<Object>
    }
}
