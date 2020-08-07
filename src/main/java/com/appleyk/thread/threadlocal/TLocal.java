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
        Map<String,Object> maps = new HashMap<>();
        maps.put("name","appleyk");
        int h = "dasdaewadadasda".hashCode();
        System.out.println(h);
        System.out.println(h >>> 16);

        int a = 4; // 0100
        int b = 8; // 1000
        System.out.println("a|b (位或，两个只要有一个为1就是1) = "+ (a|b));
        System.out.println("a^b (位异或，相同则为0，不相同则为1)= "+ (a^b));
        System.out.println("a&b (位与，两个都为1才为1) = "+ (a&b));

    }
}
