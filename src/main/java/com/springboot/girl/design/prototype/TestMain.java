package com.springboot.girl.design.prototype;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/6/27
 */
public class TestMain {

    public static void main(String[] args) throws CloneNotSupportedException {
        Resume r1 = new Resume("张三",23,new WorkExperience("联创","2016-2018"));
        Resume r2 = r1.clone();
        r2.getWorkExperience().setUnitName("投哪");
        r2.getWorkExperience().setWorkTime("2018-2019");
        System.out.println(r1);
        System.out.println(r2);
    }
}
