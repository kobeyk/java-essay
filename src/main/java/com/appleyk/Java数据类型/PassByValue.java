package com.appleyk.Java数据类型;

/**
 * <p>参数按值传递</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/15 9:37 PM
 */
public class PassByValue {

    public static void main(String[] args) {

        int a = 1;
        int b = 2;
        intSwap(a,b);
        System.out.println("====== =======");
        System.out.println("in main(), a = " + a);
        System.out.println("in main(), b = " + b);
        System.out.println("====== =======");
        char c = 'a';
        char d = 'b';
        charSwap(c,d);
        System.out.println("====== =======");
        System.out.println("in main(), c = " + c);
        System.out.println("in main(), d = " + d);

        /**
         * 输出结果：
         * in swap(), a = 2
         * in swap(), b = 1
         * ====== =======
         * in main(), a = 1
         * in main(), b = 2
         * ... ... ... ...
         *
         * 参数按值传递，其实就是copy了一个原始参数变量的副本，副本的值发生改变，并不会影响到原始变量的值
         * 所以，在swap中，虽然a和b的值进行了互换，但在main函数中，a和b变量的值是不会受到影响的
         *
         * 得到结论：对于基本类型，如short、char、bytes、int、long、float、double、boolean
         * 以上八种类型，在按值传递调用函数时，是不会改变变量在原函数中的值的
         */
    }

    /**
     * 整型互换
     */
    static void intSwap(int a , int b){
        int temp = a;
        a = b ;
        b = temp;
        System.out.println("in swap(), a = " + a);
        System.out.println("in swap(), b = " + b);
    }

    /**
     * 字符型互换
     */
    static void charSwap(char c , char d){
        char temp = c;
        c = d ;
        d = temp;
        System.out.println("in swap(), c = " + c);
        System.out.println("in swap(), d = " + d);
    }

}
