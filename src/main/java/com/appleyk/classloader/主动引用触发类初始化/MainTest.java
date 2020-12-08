package com.appleyk.classloader.主动引用触发类初始化;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  8:50 下午 2020/12/8
 */
public class MainTest {
    public static void main(String[] args) {
        // 调用类的静态常量，类没有被初始化
        //System.out.println("年龄："+A.age);
        // 调用类的静态变量，类被初始化了
        //System.out.println(A.name);
        //// 调用类的静态方法，类被初始化了
        //System.out.println(A.show());
        //new一个A类型的数组，类没有被初始化
        //A[] a = new A[1];
        //System.out.println("证明我new数组了（注意不是对象）");
        // 调用子类引用父类的静态变量，父类初始化，子类不初始化
        //System.out.println(A.id);
        //Father father = new Father();
        System.out.println(A.getId());
    }
}
