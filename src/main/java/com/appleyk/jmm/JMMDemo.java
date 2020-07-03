package com.appleyk.jmm;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class JMMDemo {
    private static int a = 1;
    public int add(){
        int c = 1;
        int d = 2;
        int sum = (c+d-a)*10;
        return sum;
    }

    public static void main(String[] args) {
        JMMDemo demo1 = new JMMDemo();
        new Thread(()->{
            demo1.add();
            System.out.println("sub1 = "+demo1.add());
        }).start();
        new Thread(()->{
            demo1.add();
            System.out.println("sub1 = "+demo1.add());
        }).start();
    }
}
