package com.appleyk.service.impl;

import com.appleyk.service.UserService;

/**
 * <p>用户接口实现类</p>
 *
 * @author appleyk
 * @version V.0.1.1
 * @blob https://blog.csdn.net/appleyk
 * @date created on 2020/5/18 9:46 PM
 */
public class UserServiceImpl implements UserService {

    @Override
    public void save() {
        System.out.println("插入一条用户记录。");
    }
}
