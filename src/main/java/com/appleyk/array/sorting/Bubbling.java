package com.appleyk.array.sorting;

import java.util.concurrent.TimeUnit;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Bubbling {
    static int[] intArr = new int[]{24,12,0,9,8,35,6,7};
    public static void main(String[] args) throws Exception{
        for (int i = 0; i < intArr.length-1; i++) {
            for (int j = 0; j < intArr.length-i-1; j++) {
                if(intArr[j]>intArr[j+1]){
                    // 冒泡排序，每次比较都要进行N-1次的交换
                    swap(j,j+1);
                    show(intArr);
                    TimeUnit.SECONDS.sleep(1);
                }
            }
        }
    }

    public static void show(int[] arr){
        for (int i : arr) {
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public static void swap(int pre,int next){
        int temp = intArr[pre];
        intArr[pre] = intArr[next];
        intArr[next] = temp;
    }
}
