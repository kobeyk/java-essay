package com.appleyk.leetcode;

import java.io.IOException;

public class LeeCodeMain {

    private static int maxCommonDivisor(int a,int b){
        for (int i = Math.max(a,b);i>0;i--){
            if(a%i == 0 && b%i ==0){
                return i;
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(maxCommonDivisor(24,32));
    }
}
