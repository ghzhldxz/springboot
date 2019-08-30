package com.springboot.girl.error;

/**
 * @Description 用静态内部类实现单例且保证线程安全
 * 小结
 * 1. 静态属性调用会触发 静态属性和静态代码块的初始化，但并不会实例化对象
 *     A a = Father.a;
 *     B b = Son.b;  先初始化父类的静态属性、静态代码块
 * 2.
 * @Author GuanHuizhen
 * @Date 2019/7/10
 */
public class JDKVersionChange {

    public static void main(String[] args) {
        //String t = getString();
        String a = String.valueOf(getString());
    }

    public static <T> T getString () {
        return (T) "ABC";
    }

}

