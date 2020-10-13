package com.appleyk.leetcode.手写Map20200806;

/**
 * <p>
 *
 *     越努力，越幸运
 *     手动实现Map接口，仿写JDK1.7版本的HashMap
 *
 * </p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public interface MyMap<K,V> {

    /** 返回Map中所有元素的个数 */
    int size();

    /** 往Map中放一个元素，并返回旧元素的值 */
    V put(K k,V v);

    /** 通过Key拿到Value */
    V get(K k);

    interface Entry<K,V>{
        /**获取key*/
        K getKey();
        /**获取value*/
        V getValue();
        /**设置Value*/
        V setValue(V v);
    }

}
