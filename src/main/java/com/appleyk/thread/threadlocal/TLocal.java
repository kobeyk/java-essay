package com.appleyk.thread.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class TLocal {
    public static void main(String[] args) {
        Map<String, Object> maps = new HashMap<>();
        maps.put("name", "appleyk");
        int h = "dasdaewadadasda".hashCode();
        System.out.println(h);
        System.out.println(h >>> 16);

        int a = 4; // 0100
        int b = 8; // 1000
        System.out.println("a|b (位或，两个只要有一个为1就是1) = " + (a | b));
        System.out.println("a^b (位异或，相同则为0，不相同则为1)= " + (a ^ b));
        System.out.println("a&b (位与，两个都为1才为1) = " + (a & b));
        System.out.println("是否是奇数：" + ((9 & 1) != 0));
        System.out.println(67 & 1);
        System.out.println(9 << 1);// 1001 --> 10010 = 16+2 = 18
        System.out.println(32 << 1);// 0010 0000 --> 0100 0000 = 2^6 = 64
        System.out.println("32除以2 = "+(32>>1));
        System.out.println("32除以4 = "+(32>>2));
        // 求余即取模运算，可以用位与运算符替代
        System.out.println(25 % 8 == (25 & (8 - 1)));
        // 1000 & 0111 结果等于 0
        System.out.println(8 % 8 == (8 & (8 - 1)));
        // 0111 & 0111 结果等于 0111 = 7
        System.out.println("7 % 8 = "+ 7%8+","+(7 % 8 == (7 & (8 - 1))));
        System.out.println("1 = "+(1<<12));
    }
}
