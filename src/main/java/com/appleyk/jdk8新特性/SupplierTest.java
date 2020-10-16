package com.appleyk.jdk8新特性;

import java.util.Optional;
import java.util.Random;
import java.util.function.Supplier;

/**
 * <p>供给型接口，无条件从它这里获取东西</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk/dubbo-spring-boot-sample
 * @date created on 11:24 2020/10/16
 */
public class SupplierTest {
    public static void main(String[] args) {
        Supplier<Integer> supplier= ()->new Random().nextInt(20);
        System.out.println(supplier.get());
        System.out.println(supplier.get());
        System.out.println(supplier.get());
        System.out.println(supplier.get());
        Optional<Integer> optional= Optional.empty();
        // 如果不为空，就取一个
        int rand = optional.orElseGet(supplier);
        System.out.println("随便取出一个："+rand);
    }
}
