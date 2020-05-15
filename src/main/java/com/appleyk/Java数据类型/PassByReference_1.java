package com.appleyk.Java数据类型;

/**
 * <p>参数按引用传递</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/15 9:55 PM
 */
public class PassByReference_1 {

    public static void main(String[] args) {

        String[] names = new String[]{"Appleyk","阿坤"};
        System.out.println("交换前：");
        System.out.println("names[0] = "+ names[0]);
        System.out.println("names[1] = "+ names[1]);
        arraySwap(names);
        System.out.println("交换后:");
        System.out.println("names[0] = "+ names[0]);
        System.out.println("names[1] = "+ names[1]);

        /**
         * 输出结果：
         *
         * 交换前：
         * names[0] = Appleyk
         * names[1] = 阿坤
         * 交换中
         * names[0] = 阿坤
         * names[1] = Appleyk
         * 交换后:
         * names[0] = 阿坤
         * names[1] = Appleyk
         *
         * 按Java说的，只有按值传递的概念，那变量names传递到swap函数中的应该是该names数组值的副本
         * 按照参数按值传递的特点来说，上面的输出结果是有问题的，那为什么，最后得到的结果却是相反的呢
         * 这个时候，我们开始怀疑Java官方说的是不是有问题
         * "怎么可能只有按值传递啊，你看，上面这段代码如果按按值传递的说辞来看，就是有问题的！"
         * 别急，这里说的值传递，其实传递的是数组在内存中的地址，地址就是所谓的 "值"
         * 也就是我们字面理解其实还是按值传递，如果从实际看到的效果来看，就是按引用传递！
         * 对同一块内存地址中的内容进行修改，必然会影响到原始变量的值。
         *
         */

    }

    static void arraySwap(String array[]){
        String temp = array[0];
        array[0] = array[1];
        array[1] = temp;
        System.out.println("交换中");
        System.out.println("names[0] = "+ array[0]);
        System.out.println("names[1] = "+ array[1]);
    }


}


