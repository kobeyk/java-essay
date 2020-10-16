package com.appleyk.jdk8新特性;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * <p>消费型接口</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk/dubbo-spring-boot-sample
 * @date created on 11:14 2020/10/16
 */
public class ConsumerTest {

    public static void main(String[] args) {

        // 消费型接口
        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer num) {
                System.out.println(num);
            }
        };
        consumer.accept(1);
        consumer=i -> System.out.println(i);
        consumer.accept(2);
        consumer = System.out::print;
        consumer.accept(3);
        System.out.println();
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        list.stream().forEach(consumer);
        System.out.println();
        list.stream().forEach(System.out::print);

    }


}
