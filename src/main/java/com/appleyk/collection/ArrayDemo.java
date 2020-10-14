package com.appleyk.collection;

import java.lang.reflect.Array;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:06 下午 2020/10/12
 */
public class ArrayDemo {
    public static void main(String[] args) {
        /**Array是反射包中的一个final类，无法被继承，且构造器是private，外部无法new实例化*/
        /**只能通过newInstance来创建数据，其是一个静态方法，调用的是本地方法，通过调用C++语言进行内存申请*/
        /**newInstance方法，可以创建一维数组，也可以创建多维数据*/
        /**为什么不提供public构造器呢，因为数组的类型太多，*/
        Object array = Array.newInstance(int.class, 5);
        Array.set(array,1,1);
        Array.set(array,2,2);
        int[] a = new int[]{1,2};
        System.out.println(Array.get(array,2));
    }
}
