package com.appleyk.jdk8新特性;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * <p>功能型接口，用于将一种类型的数据转化为另外一种类型的数据</p>
 *
 * @author Appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @github https://github.com/kobeyk/dubbo-spring-boot-sample
 * @date created on 11:40 2020/10/16
 */
public class FunctionTest {
    static class User{
        String name;
        int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public int getAge() {
            return age;
        }
    }
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("a",1));
        users.add(new User("b",2));
        Function<User,Integer> function = User::getAge;
        users.stream().map(function).forEach(System.out::println);

    }


}
