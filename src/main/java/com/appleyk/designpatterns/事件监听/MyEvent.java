package com.appleyk.designpatterns.事件监听;

/**
 * <p>事件</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 14:48 2021/2/20
 */
public class MyEvent {

    /**假如事件有一个值*/
    private int val;

    public MyEvent() {

    }

    public MyEvent(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
