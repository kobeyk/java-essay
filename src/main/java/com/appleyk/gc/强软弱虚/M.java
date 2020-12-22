package com.appleyk.gc.强软弱虚;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  10:04 下午 2020/12/22
 */
public class M {
    // 在发生gc时，被当做垃圾的对象会被回收，然后调用这个方法
    @Override
    protected void finalize() throws Throwable {
        System.out.println("我被干掉了，你再也见不到我了！");
    }
}
