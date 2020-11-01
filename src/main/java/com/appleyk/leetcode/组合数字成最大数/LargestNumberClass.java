package com.appleyk.leetcode.组合数字成最大数;

import java.util.Arrays;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  1:54 下午 2020/11/1
 *
 * 给定一组非负整数 nums，重新排列它们每位数字的顺序使之组成一个最大的整数。
 * 注意：输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * 示例 1：
 *
 * 输入：nums = [10,2]
 * 输出："210"
 * 示例 2：
 *
 * 输入：nums = [3,30,34,5,9]
 * 输出："9534330"
 * 示例 3：
 *
 * 输入：nums = [1]
 * 输出："1"
 * 示例 4：
 *
 * 输入：nums = [10]
 * 输出："10"

 */
public class LargestNumberClass {

    public static String largestNumber(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .map(n->Integer.toString(n))
                .sorted((s1,s2)-> {return (s2+s1).compareTo(s1+s2);})
                .reduce((s1,s2)->s1.concat(s2))
                .filter(s->!s.startsWith("0"))
                .orElse("0");
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,30,3,45,9};
        System.out.println(largestNumber(nums));
    }

        //public static void main(String[] args) {
        //    int[] nums = new int[]{1,2,30,3,45,9};
        //    String s3 = Arrays.stream(nums).boxed()
        //            .map(i -> Integer.toString(i))
        //            .sorted((s1, s2) -> {
        //                // 自定义排序，首先这两个字符串的长度必然相等
        //                // 所以要比较的就是各个位置上的谁大，如果有一个大，那就是s1要排前面
        //                String sum1 = s1 + s2;
        //                String sum2 = s2 + s1;
        //                // 随便遍历一个字符串，比较大小
        //                //for (int i = 0; i < sum1.length(); i++) {
        //                //    if (s1.charAt(i) != s2.charAt(i)) {
        //                //        //返回负数，就是s2小s1，返回正数就是s2大于s1
        //                //        return s2.charAt(i) - s1.charAt(i);
        //                //    }
        //                //}
        //                //return 0;
        //                return sum2.compareTo(sum1);
        //            }).reduce((s1, s2) -> s1.concat(s2))
        //            .filter(s->!s.startsWith("0")).orElse("0");
        //    System.out.println(s3);
        //}
}
