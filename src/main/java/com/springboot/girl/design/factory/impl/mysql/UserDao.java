package com.springboot.girl.design.factory.impl.mysql;

import com.springboot.girl.bean.User;
import com.springboot.girl.design.factory.IUser;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class UserDao implements IUser {
    @Override
    public User getUserById(int id) {
        System.out.println("我是mysql查询出的user");
        return null;
    }
}
