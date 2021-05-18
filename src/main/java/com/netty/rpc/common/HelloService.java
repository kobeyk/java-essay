package com.netty.rpc.common;

/***
 * 接口中定义的变量默认就是常量，因为接口不能被实例化，成员变量要么被实现，要么不能修改
 * 被实现的是方法，不能修改的是字段，既然不能修改肯定是常量，接口中定义的成员默认都是public的
 * 又接口不能实例化，所以定义的字段必须是静态的
 */
public interface HelloService {
    String FLAG = "HelloService#say#";
    String say(String content);
}
