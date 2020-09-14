package com.appleyk.classloader;

/**
 * <p>JVM类加载 -- 被动引用</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  8:22 下午 2020/9/14
 */

/**
 * 被动使用类字段演示一
 * 通过子类来调用父类的静态字段，JVM并不会对子类进行初始化操作
 */
class SuperClass{
    static {
        System.out.println("SuperClass init.");
    }

    /**注意，类的静态变量放在堆里的，而类的静态常量，在编译时是放在常量池中的
     * 因此，加final和不加final，main方法执行的打印结果不一样
     */
    public static int VALUE = 1;
}

class SubClass extends SuperClass{
    static {
        System.out.println("SubClass init.");
    }
}

/**
 * 3、被动使用类字段演示三
 * 常量在编译时会存入调用类（注意是调用的）的常量池中，
 * 本质上并没有直接引用到定义（注意是定义）常量的类，因此定义该常量的类并不会被初始化
 */
class ConstClass{
   static {
       System.out.println("ConstantsClass init.");
   }
   public static final int VALUE = 2;
}

class Test{
    //public static int i = 0;
    static {
        i = 0;
        //System.out.println(i);
        //上面这一行代码编译将不通过，会提示向前引用，但是将i的定义放到static块之前就可以，放在之后不行
    }
    public static int i = 1;
}

class Person{
    public static int AGE = 1;
    static {
        AGE = 2;
    }
}

class Student extends Person{
    public static int SAGE = AGE;
}

public class NoInitialization {

    public static void main(String[] args) {
        // 1、这里虽然用子类调用了父类中的静态变量，但是，这里只有父类才进行了初始化，而子类没有
        // 也就是，对于静态字段，只有直接定义了这个字段的类才会被初始化
        System.out.println(SubClass.VALUE);

        // 2、通过数组定义来引用类，不会触发类的初始化
        SuperClass[] supers = new SuperClass[10];

        // 3、ConstClass的常量值2，存到了当前NoInitialization类中的常量池中
        System.out.println(ConstClass.VALUE);

        // 4、类中的静态（static）块中只能访问之前的变量，之后的无法访问
        System.out.println(Test.i);

        // 5、下面究竟是1呢还是2呢？ 答案显然是2，因为父类中定义的静态语句块执行将优于子类变量的赋值
        System.out.println(Student.SAGE);
    }

    /***
     * JVM打印的类加载信息
     * [Loaded com.appleyk.classloader.SuperClass from file:/Users/apple/Appleyk/github/java-essay/target/classes/]
     * [Loaded com.appleyk.classloader.SubClass from file:/Users/apple/Appleyk/github/java-essay/target/classes/]
     * SuperClass init.
     * 1
     * [Loaded java.net.Inet6Address$Inet6AddressHolder from /Library/Java/JavaVirtualMachines/jdk1.8.0_191.jdk/Contents/Home/jre/lib/rt.jar]
     *
     * 可以看到，先是所需的类被加载，然后是执行类的初始化，但是我们发现，只有SuperClass执行了初始化
     */

}
