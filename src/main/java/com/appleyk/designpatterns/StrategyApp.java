package com.appleyk.designpatterns;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class StrategyApp {
    public static void main(String[] args) {
        User[] users = new User[]{
                new User(18,180),
                new User(25,175),
                new User(6,120)
        };
        Sorter<User> sorter =new Sorter<>();
        sorter.sort(users, new UserComparator());
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("==== 下面开始 比较用户的身高 ==== ");
        sorter.sort(users,(o1,o2)->{
            if(o1.getHeight()<o2.getHeight()){
                return -1;
            }else if(o1.getHeight()>o2.getHeight()){
                return 1;
            }
            return 0;
        });
        for (User user : users) {
            System.out.println(user);
        }
    }
}
