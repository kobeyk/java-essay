package com.appleyk.string;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  7:53 上午 2020/9/1
 */
public class StrIntern {

    //如果常量池中包含了一个和当前对象相等的字符串则返回常量池中的字符串，
    //否则把该对象的字符串放到常量池中，同时将该对象的引用指向常量池中字符串的地址，
    //String.intern()

    public static void main(String[] args) {

        /**1、JDK8 下面输出结果是什么？ 为什么是这样的？*/
        String s1 = new String("1");// #1
        s1.intern();// #2
        String s2 = "1"; // #3
        System.out.println("1:"+(s1 == s2)); // #4
        /**
         * 答案:false
         * 解答:
         * (1) #1行代码，干了两件事情，一件是在常量池中创建了1这个字符串对象，另一件是new了一个对象，s1指向这个对象
         *      注意，JDK1.7后，常量池被移到了堆中
         * (2) #2行代码，将s1的值放入到常量池中，因为在#1时，1已经存在于常量池中了，所以执行这个并不会再次放入
         *      所以，s1指向的内存地址并非常量池中1的地址，而是堆中new的那个对象的地址
         * (3) #3行代码，声明了变量s2，它引用了堆中常量池中的1对象的地址
         * (4) #4行代码，由于s1引用的是堆中对象的地址，而对象的值指向的又是堆中常量池中的1对象
         *      而s2是直接引用的堆中常量池中的1对象，因此s1和s2虽然值都是1，但是指向的地址却不一样，因此==结果是false
         *
         */

        /**2、JDK8 下面输出结果是什么？ 为什么是这样的？*/
        String s3 = new String("1") + new String("1");  // #1
        s3.intern(); // #2
        String s4 = "11"; // #3
        System.out.println("2:"+(s3 == s4)); // #4
        /**
         * 答案:true
         * 解答:
         * (1) #1行代码，干了三件事情，一件是在常量池中创建了1这个对象，一件是new了两个值均为1的对象
         *     最后一件事是将两个对象进行字符串的+（实际上调用的是StringBuilder的append方法）运算得到一个新对象，
         *     新对象其实是调用StringBuilder的toString方法来的，而toString方法中是这样色的：
         *     return new String(value, 0, count); #407
         *     value是谁呢？ append两次后，value固然是11，s3指向这个新对象
         *     注意，JDK1.7后，常量池被移到了堆中
         * (2) #2行代码，先判断常量池中有没有11这个字符串对象，显然没有，于是把11放入到常量池中，同时s3指向常量池中11这个对象的地址
         * (3) #3行代码，声明了变量s4，由于11已经在常量池中存在了，所以s4直接指向这个常量池中的11字符串的地址
         * (4) #4行代码，由于s3和s4变量引用的地址都是常量池中11字符串的地址，因此==结果是true
         *
         */

        /**3、JDK8 下面输出结果是什么？ 为什么是这样的？*/
        String s5 = "how"; // #1
        String s6 = s5 + " old are you"; // #2
        System.out.println("3:"+("how old are you".equals(s6))); // #3
        System.out.println("4:"+("how old are you" == s6)); // #4

        /**
         * 答案:true，false
         * 解答:
         * (1) #1行代码，s5指向常量池中值为how字符串对象的地址
         *     注意，JDK1.7后，常量池被移到了堆中
         * (2) #2行代码，干了两件事情，一个是将old are you放入常量池
         *     一个是将how和old are you进行append操作得到新对象how old are you，s6指向这个新对象
         * (3) #3行代码，不用想，只要是字符串的equals，判断依据就是值完全相等，显然结果是：true
         * (4) #4行代码，how old are you是在常量池中的，而s6是堆中new出来的普通对象，二者引用的内存地址显然不一样
         *     固 ==的结果为：false
         *
         */

        /**4、JDK8 下面输出结果是什么？ 为什么是这样的？*/
        String s7 = "how old are you"; // #1
        String s8 = new String("how old are you"); // #2
        System.out.println("5:"+s7.equals(s8)); // #3
        System.out.println("6:"+(s7 == s8)); // #4
        /**
         * 答案:true，false
         * 解答:
         * (1) #1行代码，s7指向常量池中值为how old are you的字符串对象的地址
         *     注意，JDK1.7后，常量池被移到了堆中
         * (2) #2行代码，干了两件事情，一个是判断常量池中how old are you字符串对象是不是存在，如果不存在，往里放，这里显然存在
         *     一个是new了一个对象，其值指向这个常量池中的how old are you字符串对象，这个对象的地址被s8引用
         * (3) #3行代码，不用想，只要是字符串的equals，判断依据就是值完全相等，显然结果是：true
         * (4) #4行代码，how old are you是在常量池中的，而s8是堆中new出来的普通对象，二者引用的内存地址显然不一样
         *     固 ==的结果为：false
         *
         */

        /**5、JDK8 下面输出结果是什么？ 为什么是这样的？*/
        String s9 = "how"; // #1
        String s10 = s9.concat(" old are you"); // #2
        System.out.println("7:"+("how old are you".equals(s10))); // #3
        System.out.println("8:"+("how old are you" == s10)); // #4
        System.out.println("9:"+("how old are you" == s10.intern())); // #4
        /**
         * 答案:true，false，true
         * 解答:
         * (1) #1行代码，s9指向常量池中值为how的字符串对象的地址
         *     注意，JDK1.7后，常量池被移到了堆中
         * (2) #2行代码，干了两件事情，一个是判断常量池中old are you字符串对象是不是存在，如果不存在，往里放，这里显然不存在，往里放
         *     一个是将字符串常量how和old are you进行连接得到新对象，其值是how old are you，然后s10指向这个对象
         * (3) #3行代码，不用想，只要是字符串的equals，判断依据就是值完全相等，显然结果是：true
         * (4) #4行代码，how old are you是在常量池中的，而s10是堆中new出来的普通对象，二者引用的内存地址显然不一样
         *     固 ==的结果为：false
         * (5) #5行代码，调用s10的intern方法，会先判断常量池中是不是存在how old are you字符串，显然存在，不会放,直接返回常量字符串
         *     因此，拿常量池中how old are you和自身进行==比较，答案显然是：true
         *
         */
    }
}
