package com.appleyk.jvm.字符串常量池;

/**
 * <p>越努力，越幸运</p>
 * 经典面试题
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  8:26 下午 2021/3/11
 */
public class StringIntern01 {
    public static void main(String[] args) {
        /**
         * 这个无论哪个版本，都是false，因为s指向是的堆的地址，s2指向的是常量池的地址，两个地址肯定不一样，所以没有可比性
         */
        String s = new String("1");
        s.intern();
        String s2 = "1";
        System.out.println(s == s2);// jdk6：false，jdk7/8： false

        /**
         * 1、只要涉及到字符串+，都会new一个StringBuilder（非线程安全）对象
         * 2、new String("a")
         * 3、ldc（常量池中的常量入栈）一个对象a
         * 4、new String("b")
         * 5、ldc（常量池中的常量入栈）一个对象b
         * 6、最后要调用StringBuilder的toString方法
         *    这个toString方法，又会创建一个new String()对象
         */
        String m1 = new String("a") + new String("b");

        /** 我们知道，在常量池中是不存在ab字符串（通过字节码可以看出）的，
         *  因此m1调用intern方法，jdk7的做法是将当前m1的对象的引用复制一份到常量池（省空间）
         *  而在jdk6中，是将m1这个对象复制一份到常量池，注意一个是引用的地址，一个是对象本身
         *  因此jdk6中，m1和m2的值虽然相等，但是一个指向堆，一个指向常量池，地址明显不一样，false
         *  jdk7中就不同了，常量池中的ab指向的是堆中，也就是m1指向的地址，显然m1和m2指向的都是同一块区域，true

         */
        m1.intern();
        String m2 = "ab";
        System.out.println(m1 == m2); // jdk6:false jdk7/8:true


    }
}
