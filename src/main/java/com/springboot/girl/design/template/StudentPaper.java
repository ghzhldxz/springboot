package com.springboot.girl.design.template;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/6/27
 */
public class StudentPaper extends TestPaper {

    public StudentPaper(String name) {
        super(name);
    }

    @Override
    String answer1() {
        return "A";
    }

    @Override
    String answer2() {
        return "A";
    }
}
