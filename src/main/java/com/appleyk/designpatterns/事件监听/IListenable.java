package com.appleyk.designpatterns.事件监听;

/**
 * <p>事件监听接口（具体监听业务自己实现）</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk
 * @date created on 14:47 2021/2/20
 */
public interface IListenable {
    /**
     * 当事件源发布的事件发生改变时，及时监听做出处理
      * @param ev 事件对象
     */
   void eventChange(MyEvent ev);
}
