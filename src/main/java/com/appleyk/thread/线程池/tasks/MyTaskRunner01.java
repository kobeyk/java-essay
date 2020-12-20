package com.appleyk.thread.线程池.tasks;

/**
 * <p>越努力，越幸运</p>
 * 自定义任务执行器，定义两个方法，一个是正常输出的，一个是输出后跑异常的
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  4:23 下午 2020/12/20
 */
public class MyTaskRunner01 {
    public static void sayHiWithExp(String methodName){
        String threadName ="线程 - "+ Thread.currentThread().getName()+"，执行方法`"+methodName;
        System.out.println("大家好，我是："+threadName);
        throw new RuntimeException("不好了，出错了！");
    }
    public static void sayHi(String methodName){
        String threadName ="线程 - "+ Thread.currentThread().getName()+"，执行方法`"+methodName;
        System.out.println("大家好，我是："+threadName);
    }

    public static String sayHiWithReturn(String methodName){
        String threadName ="线程 - "+ Thread.currentThread().getName()+"，执行方法`"+methodName;
        return "大家好，我是："+threadName;
    }
}
