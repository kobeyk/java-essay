package com.appleyk.designpatterns.事件监听;

/**
 * <p></p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 15:03 2021/2/20
 */
public class EventMain {
    public static void main(String[] args) {
        MyEventSource source = new MyEventSource();
        source.addListener(new MyListener());
        source.setVal(1);
        source.setVal(2);
        source.setVal(3);
    }
}
