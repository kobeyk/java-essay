package com.appleyk.leetcode.整数反转20200802;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.Scanner;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class LongReverse {

    // 9223372036854775807
    private static final long LG_MAX = Long.MAX_VALUE;
    // -9223372036854775808
    private static final long LG_MIN = Long.MIN_VALUE;

    /**
     * 取模运算，拿到个位数，弹出保存，外加long类型的【min，max】范围检查
     *
     * @param val
     * @return
     */
    public static long reverse(long val) {
        BigInteger
        long res = 0;
        while (val != 0) {

            // 1、将个位数弹出来，记录弹出的值
            long pop = val % 10;
            // 2、然后除10,保存弹出个位数后的值
            val /= 10;

            // 3、关键一步来了，想要反转弹出的值，必须要对res的值和pop的值进行验证
            // 3.1、是否正数越界
            // (很简单，反转后的res超过最大值/10 或者 弹出最后一位的时候，pop值超过最大值的个位数)
            boolean wpCrossBorder = res > LG_MAX / 10 || (LG_MAX / 10 == res && pop > LG_MAX % 10);

            // 3.2、是否负数越界
            // (很简单，反转后的res小于最小值/10 或者 弹出最后一位的时候，pop值小于最小值的个位数)
            boolean wnCrossBorder = res < LG_MIN / 10 || (LG_MIN / 10 == res && pop < LG_MIN % 10);

            if (wpCrossBorder) {
                // 正数越界就是0
                System.out.println("正数Long值反转后发生溢出.");
                return 0;
            } else if (wnCrossBorder) {
                // 负数越界也是0
                System.out.println("负数Long值反转后发生溢出.");
                return 0;
            }

            // 4、res和pop值逃过验证后，就是重新计算反转值了
            // (很简单，来一个数就✖️10，然后加上弹出的个位数就ok了
            //  注意res初值是0，所以反转第一个数的时候，0*10+pop是ok的！)
            res = res * 10 + pop;
        }

        return res;
    }

    /**
     * 输入字符串，反转
     * @param val
     * @return
     */
    private static long reverse(String val) {
        long res = 0;
        // 1、先字符串反转
        val = new StringBuilder(val).reverse().toString();
        // 2、判断下最后一位是不是 "-"
        boolean bLastChar = "-".equals(val.substring(val.length() - 1, val.length()));
        // 3、如果是的话，截取
        if (bLastChar) {
            val = val.substring(0, val.length() - 1);
        }
        try {
            res = bLastChar ? Long.valueOf(val) * (-1) : Long.valueOf(val);
        } catch (Exception e) {
            // 溢出了，就返回0
            System.out.println(e.getMessage());
            return 0;
        }
        return res;
    }

    //public static void main(String[] args) {
    //    //9223372036854775807,-9223372036854775808
    //    InputStream in = System.in;
    //    Scanner scanner = new Scanner(in);
    //    long val = scanner.nextLong();
    //    while (val!=0){
    //        System.out.println(reverse(val));
    //        val = scanner.nextLong();
    //    }
    //}

    public static void main(String[] args) {
        //9223372036854775807,-9223372036854775808
        InputStream in = System.in;
        Scanner scanner = new Scanner(in);
        long val = scanner.nextLong();
        while (val != 0) {
            System.out.println(reverse(String.valueOf(val)));
            val = scanner.nextLong();
        }
    }
}
