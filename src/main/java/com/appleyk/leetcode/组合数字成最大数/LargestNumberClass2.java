package com.appleyk.leetcode.组合数字成最大数;

import java.util.Arrays;
import java.util.Comparator;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  2:10 下午 2020/11/1
 */
public class LargestNumberClass2 {
    private class LargestNumber implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            String s1 = a+b;
            String s2 = b+a;
            return s2.compareTo(s1);
        }
    }

    public String largestNumber(int[] nums) {

        int len = nums.length;
        // 先把int转string
        String resArray[] = new String[len];
        for (int i = 0; i <len ; i++) {
            resArray[i] = String.valueOf(nums[i]);
        }
        // 然后排序下
        Arrays.sort(resArray,new LargestNumber());

        // 拼接前先看一下，如果最高位是0，只有一种情况，那就是所有的元素都是0
        if("0".equals(resArray[0])){
            return "0";
        }

        // 拼接，用builder，虽然线程不安全，但是性能高
        String s = "";
        for (int i = 0; i <len ; i++) {
           s+=resArray[i];
        }

        return s;
    }


}
