package com.appleyk.leetcode.手写Map20200806;

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
        MyMap<String,String> names = new TMap<>();
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
        System.out.println(names.size());
    }
}
