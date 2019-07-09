package com.springboot.girl.design.template;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/6/27
 */
public class Main {

    public static void main(String[] args) {
        StudentPaper s1 = new StudentPaper("张三");
        Student2Paper s2 = new Student2Paper("李四");

        s1.subimtTest();
        s2.subimtTest();
    }

}
