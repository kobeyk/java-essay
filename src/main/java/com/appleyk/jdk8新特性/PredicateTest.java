package com.appleyk.jdk8新特性;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * <p>判断型接口</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk/dubbo-spring-boot-sample
 * @date created on 11:31 2020/10/16
 */
public class PredicateTest {
    public static void main(String[] args) {
        Predicate<Integer> predicate = i -> i>5;
        System.out.println((predicate.or(i -> i==1)).test(1));
        Consumer<Integer> consumer = System.out::print;
        System.out.println(predicate.test(1));

        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        // 只要偶数
        list.stream().filter(predicate).forEach(consumer);
    }
}
