package com.appleyk.leetcode.大数相加20200803;

/**
 * <p>

 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 *
 * 提示：
 *
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 *
 * </p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class Solution {
    public static String add(String num1,String num2){
        // 1、分别获取num1和num2的len
        int len1 = num1.length(),len2 = num2.length();
        // 2、对齐位进行数值相加,相加的结果的进位值用add变量保存
        int add = 0;
        // 3、对齐位相加的结果，要存起来，使用builder虽然线程不安全，但是效率高
        StringBuilder sb = new StringBuilder();
        while(len1 > 0 || len2 >0){
            // 0对应的ASCII码值等于48，char转int，-'0'得到ASCII差值也就是真实的int值，反之int转char，+'0'
            // 一定要注意，短的字符，对齐位要补0，要是不补的话，长的字符多出来的部分没法进行加法运算
            int x = len1 > 0 ? num1.charAt(len1-1) - '0' : 0;
            int y = len2 > 0 ? num2.charAt(len2-1) - '0' : 0;
            // 第一次进入循环对两个数的个位数进行相加时，add是没有进位值的
            int result = x + y + add;
            // 将计算的结果求模得到的值存起来，这个值很关键
            sb.append(result % 10);
            // 一旦相加后，求出进位值，求的方法是除以10，得到的值即为向前进位的数值
            // 比如，9+8=17，进位的值 add = 17/10 = 1，2+3=5，进位的值 add = 5/10 = 0
            add = result / 10;
            // 记得每加一个数，剔除一次加法运算
            len1--;
            len2--;
        }
        // 如果两个数所有的位上的数字都相加完了，最后还有进位的话，一定要记得再次存一下
        // 比如91+9,最后add值必然是1，如果不把1放进去的话,得到的最终结果就是00
        if(add!=0){
            sb.append(add);
        }
        // 最终，要把sb的值进行翻转
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(add("983","17"));
        System.out.println(add("123456789123456712","17"));
        System.out.println(add("9999999999999999999999299","701"));
    }
}
