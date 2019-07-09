package com.springboot.girl.design.factory;

/**
 * @Description 数据库访问接口
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class DataAccessor {

    public static IUser getUserDao() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //下面的字符串可以走配置文件
        IUser user = (IUser) Class.forName("com.springboot.girl.design.factory.impl.mysql.UserDao").newInstance();
        return user;
    }

    public static IDepartment getDepartmentDao() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //下面的字符串可以走配置文件
        IDepartment department = (IDepartment) Class.forName("com.springboot.girl.design.factory.impl.mysql.DepartmentDao").newInstance();
        return department;
    }

}
