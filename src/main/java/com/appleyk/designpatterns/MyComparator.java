package com.appleyk.designpatterns;

/**
 * <p>越努力，越幸运 -- 自定义比较器</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
@FunctionalInterface
public interface MyComparator<T> {
    int compare(T o1,T o2);
    default void show(){
        System.out.println("show 你一脸 ！");
    }

}
