package com.appleyk.jdk8新特性;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on  下午9:52 2021/5/27
 */
public class BiConsumerTest {
    public static void main(String[] args) {
        Map<String,Integer> rMap = new HashMap<>();
        rMap.put("苹果手机",100);
        rMap.put("华为手机",200);
        rMap.put("小米手机",300);
        rMap.put("锤子手机",400);
        for (Map.Entry<String, Integer> entry : rMap.entrySet()) {
            System.out.println(entry.getKey()+"----"+entry.getValue());
        }
        System.out.println("======================");
        BiConsumer<String,Integer> bi = new BiConsumer<String, Integer>() {
            @Override
            public void accept(String v, Integer k) {
                System.out.println(v+"----"+k);
            }
        };
        rMap.forEach(bi);
        System.out.println("======================");
        rMap.forEach((k,v)-> System.out.println(v+"----"+k));
    }
}
