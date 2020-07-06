package com.appleyk.array.sorting;

/**
 * <p>越努力，越幸运 -- 选择排序</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class SelectSort {
    static int[] intArr = new int[]{24,12,0,9,8,35,6,7};
    public static void main(String[] args) throws Exception{
        int min;
        for (int i = 0; i < intArr.length-1; i++) {
            // 从第0个开始
            min = i;
            for (int j = i+1; j < intArr.length; j++) {
                // 找最小的值的下标
                if(intArr[j]<intArr[min]){
                   min = j;
                }
            }
            // 找到一次，把最小的那个下标和当初假设最小的那个下标的元素替换一下
            // 选择排序相比较冒泡来说，比较的次数是一样的，但是交换的次数明显少于冒泡
            swap(i,min);
        }
        show(intArr);
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
