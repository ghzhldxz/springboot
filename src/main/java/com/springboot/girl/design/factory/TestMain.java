package com.springboot.girl.design.factory;

import com.springboot.girl.design.factory.DataAccessor;
import com.springboot.girl.design.factory.IUser;

/**
 * @Description 抽象工厂模式
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class TestMain {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        IUser user = DataAccessor.getUserDao();
        user.getUserById(0);
    }
}
