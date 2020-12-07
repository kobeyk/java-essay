package com.appleyk.aqs;

import com.appleyk.aop.Father;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  9:51 下午 2020/12/7
 */
public class Son2 extends Father {
    public static void main(String[] args) {
        Father father =new Son2();
        // 父类中的protected字段，只能在同包和子类中才能访问，跨包不行！！！
        //System.out.println(father.age);
    }
}
