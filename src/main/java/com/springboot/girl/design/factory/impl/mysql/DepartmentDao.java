package com.springboot.girl.design.factory.impl.mysql;

import com.springboot.girl.bean.User;
import com.springboot.girl.design.factory.IDepartment;
import com.springboot.girl.design.factory.bean.Department;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class DepartmentDao implements IDepartment {
    @Override
    public Department getDepartmentbyUser(User u) {
        System.out.println("我是mysql查询出的department");
        return null;
    }
}
