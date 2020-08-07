package com.appleyk.leetcode.手写Map20200806;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class MapMain {
    public static void main(String[] args) {
        MyMap<String,String> names = new TMap<>(64);
        names.put("1","乔丹");
        names.put("2","科比");
        names.put("3","詹姆斯");
        names.put("4","库里");
        names.put("5","韦德");
        names.put("6","邓肯");
        names.put("7","麦迪");
        names.put("8","姚明");
        names.put("9","奥尼尔");
        names.put("10","艾弗森");
        names.put("11","安东尼");
        names.put("12","加内特");
        names.put("13","卡特");
        // 如果put的时候，不判断key值是否重复，而是直接新增一个entry的话，就失去了key值唯一的意义
        names.put("14","加索尔");
        names.put("15","洛瑞");
        names.put("16","拜纳姆");
        names.put("17","保罗");
        names.put("18","罗斯");
        names.put("19","罗伊");
        names.put("20","布克");
        names.put("21","哈登");
        names.put("22","伊巴卡");
        names.put("23","维斯布鲁克");
        names.put("24","戈登");
        names.put("25","霍华德");
        names.put("26","马龙");
        names.put("27","罗德曼");
        names.put("28","吉诺比利");
        names.put("29","诺维斯基");
        names.put("30","帕克");
        names.put("31","阿里纳斯");
        // 随着key，value键值对entry对象越来越多时，如果map还是保持默认的16的话，getValue的效率就会降低
        // 因为，hash碰撞会发生频繁，导致entry链表太长，遍历的时候，取key对应的val遍历的次数就会增加
        // 怎么办呢，好办，扩容，对原有entry的key值进行重散列，重新定位在数组中的index
        names.put("32","杜兰特");
        System.out.println("Map Size :"+names.size());
        System.out.println(names.get("23"));
    }
}
