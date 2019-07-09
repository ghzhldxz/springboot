package com.springboot.girl.design.template;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/6/27
 */
public class Student2Paper extends TestPaper {
    public Student2Paper(String name) {
        super(name);
    }

    @Override
    public String answer1() {
        return "C";
    }

    @Override
    String answer2() {
        return "D";
    }
}
