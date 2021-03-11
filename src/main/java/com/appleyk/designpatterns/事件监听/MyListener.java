package com.appleyk.designpatterns.事件监听;

/**
 * <p>自定义监听器</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 15:02 2021/2/20
 */
public class MyListener implements IListenable{
    @Override
    public void eventChange(MyEvent ev) {
        System.out.println("监听到了事件："+ev.hashCode()+",值发生了改变，val = "+ev.getVal());
    }
}
