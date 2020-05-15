package com.appleyk.Java数据类型;

/**
 * <p>按引用传递，复杂案例</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/15 10:22 PM
 */
public class PassByReference_2 {

    public static void main(String[] args) {

         Screen s = new Screen(100);
         chageWidth(s);
         System.out.println("change："+s.getWidth());
         referWidth(s);
         System.out.println("refer ："+s.getWidth());

        /**
         * 输出结果：
         * change：100
         * refer ：200
         */

    }

    /**
     * 改变参数对象的width值，并不会影响原对象
     */
    static void chageWidth(Screen s){
        Screen r = new Screen(200);
        s = r ;

        /**
         * 解读：
         * (1) 首先，我们知道，类、接口、数组都是特殊的按"值"传递，即按引用传递
         * (2) 所以，s变量【形参】实际上指向的是原始对象s【实参】所在的内存地址
         * (3) 然后，注意了，这时候，在change函数中，new了一个新对象r，只要new，就是在堆内存中开辟了一块新地址
         * (4) 此时，我们把对象r赋给变量s，如果我们稍微不注意，就会被迷惑住，想当然的认为，main函数中的s对象的width值已经变成了200
         * (5) 但是，实际上，这个赋值操作，只是把形参对象s指向的一块内存地址换成了另一块
         * (6) 因此，形参s指向的内存地址中的width值等于200，和实参s没有半毛线关系，因为二者的内存地址完全是两个不想干的！
         */
    }

    /**
     * 改变参数对象的width值，会影响原对象，因为按引用传递，是对同一块内存地址中的值进行改变
     */
    static void referWidth(Screen s){
        s.setWidth(200);
    }

}

class Screen{

    /**屏幕宽*/
    int width ;

    public Screen(int width){
        this.width = width ;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWidth() {
        return width;
    }
}

