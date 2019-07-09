package com.springboot.girl.design.factory;

import com.springboot.girl.bean.User;
import com.springboot.girl.design.factory.bean.Department;

public interface IDepartment {
    Department getDepartmentbyUser(User u) ;
}
