package com.appleyk.leetcode.数据结构.裴波纳契;

public class FibDemo {

    /**
     * 0 1 1 2 3 5 8 13 n-1+n-2 ....
     */

    public static int fib1(int n){
        if(n<2) return n;
        return fib1(n-1)+fib1(n-2);
    }

    public static int fib2(int n){
        int first = 0;
        int second = 1;
        for (int i = 0; i < n-1; i++) {
            int sum = first+second;
            first = second;
            second = sum;

        }
        return second;
    }

    public static void main(String[] args) {
        System.out.println(fib1(10));
        System.out.println(fib2(10));
    }
}
