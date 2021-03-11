package com.appleyk.designpatterns.事件监听;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>事件源</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 14:46 2021/2/20
 */
public class MyEventSource {

    private MyEvent event = new MyEvent();

    // 可以对事件源发布的具体事件做监听，当然同一个时间可以有多个监听实现
    private List<IListenable> listeners;

    public MyEventSource(){
        listeners = new ArrayList<>();
    }

    public void addListener(IListenable listenable){
        listeners.add(listenable);
    }

    public void setVal(int val) {
        event.setVal(val);
        // 只要事件源的值发生改变了，就通知回调
        eventNotify();
    }

    public void eventNotify(){
        for (IListenable listener : listeners) {
            listener.eventChange(event);
        }
    }
}
