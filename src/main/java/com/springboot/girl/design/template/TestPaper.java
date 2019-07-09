package com.springboot.girl.design.template;

/**
 * @Description 考卷
 * @Author GuanHuizhen
 * @Date 2019/6/27
 */
public abstract class TestPaper {
    protected String name;

    public TestPaper(String name) {
        this.name = name;
    }

    private void question1() {
        System.out.println("我是第1题，请问~");
        System.out.println(name+"的答案是："+answer1());
    }

    private void question2() {
        System.out.println("我是第2题，请问~");
        System.out.println(name+"的答案是："+answer2());
    }

    public void subimtTest() {
        question1();
        question2();
    }

    abstract String answer1();
    abstract String answer2();
}
