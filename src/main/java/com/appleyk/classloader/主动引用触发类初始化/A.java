package com.appleyk.classloader.主动引用触发类初始化;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  8:35 下午 2020/12/8
 */

class Father{
    static long id = 1001L;
    static long getId(){
        return id;
    }
    {
        // 类代码块，只要new对象，new一次执行一次
        System.out.println("我只有在实例化的时候，才会被执行");
    }
    static {
        // 类静态代码块，不管new多少次对象，都只执行一次
        System.out.println("父类完成初始化！");
    }
}

public class A extends Father{
    static String name = "appleyk";
    public final static int age = 18;
    static String show(){
        return name;
    }
    static {
        System.out.println("子类完成初始化！");
    }
}


