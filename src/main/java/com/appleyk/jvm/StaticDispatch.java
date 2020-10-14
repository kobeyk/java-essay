package com.appleyk.jvm;

/**
 * <p>越努力，越幸运 -- 方法静态分派</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  8:59 下午 2020/9/26
 */
public class StaticDispatch {

    static abstract class  Human{}
    static class  Men extends Human{}
    static class  Women extends Human{}

    public void say(Human human){
        System.out.println("hi,human guy !");
    }

    public void say(Men men){
        System.out.println("hi,men guy !");
    }

    public void say(Women women){
        System.out.println("hi,women guy !");
    }

    public static void main(String[] args) {
        Human men = new Men();
        Human women = new Women();
        // 虚拟机（准确来说是编译器），在重载时是通过参数的静态类型而不是实际类型最为判定依据的
        // 因此在编译阶段，javac是根据参数（变量）的静态类型来确定重载哪个版本的方法的
        StaticDispatch dispatch = new StaticDispatch();
        dispatch.say(men);
        dispatch.say(women);
    }
}


