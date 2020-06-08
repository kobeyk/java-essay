package com.appleyk.designpatterns;

/**
 * <p>越努力，越幸运</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/19 10:46 PM
 */
public class UserComparator implements MyComparator<User>{
    @Override
    public int compare(User o1, User o2) {
        if(o1.getAge()<o2.getAge()) {
            return -1;
        }else if(o1.getAge()>o2.getAge()){
            return 1;
        }
        return 0;
    }
}
