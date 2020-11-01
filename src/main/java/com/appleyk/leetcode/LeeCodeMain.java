package com.appleyk.leetcode;

public class LeeCodeMain {

    public static void main(String[] args) {
        // 循环中使用字符串拼接
        String a = new String();
        Long start = System.currentTimeMillis();
        for (int i = 1; i < 10000; i++) {
            a = a + i;
        }
        System.out.println("字符串拼接执行一万次时间：" + (System.currentTimeMillis() - start) + " 毫秒");
        // 循环外定义StringBuilder
        start = System.currentTimeMillis();
        StringBuilder b = new StringBuilder();
        for (int i = 1; i < 5000000; i++) {
            b.append(i);
        }
        System.out.println("使用StringBuilder执行五百万次时间：" + (System.currentTimeMillis() - start) + " 毫秒");

        int hash1 = 31*5;
        int hash2 = (5<<5)-5;
        System.out.println(hash1 == hash2);
    }
}
