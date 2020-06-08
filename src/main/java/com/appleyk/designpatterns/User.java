package com.appleyk.designpatterns;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class User implements MyComparable<User>{

    private Integer age;
    private Integer height;

    public User(Integer age, Integer height) {
        this.age = age;
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getHeight() {
        return height;
    }

    @Override
    public int compareTo(User o) {
        if(this.age<o.age){
            return -1;
        }else if(this.age>o.age){
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", height=" + height +
                '}';
    }
}
