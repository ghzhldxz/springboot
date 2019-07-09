package com.springboot.girl.design.prototype;

import lombok.Data;

/**
 * @Description 简历
 * @Author GuanHuizhen
 * @Date 2019/6/27
 */
@Data
public class Resume implements Cloneable{
    private String name;
    private int age;
    private WorkExperience workExperience;

    public Resume(String name, int age, WorkExperience workExperience) {
        this.name = name;
        this.age = age;
        this.workExperience = workExperience;
    }

    @Override
    protected Resume clone() throws CloneNotSupportedException {
        Resume resume = (Resume) super.clone();
        WorkExperience we = resume.getWorkExperience();
        resume.setWorkExperience((WorkExperience) we.clone());
        return resume;
    }

    @Override
    public String toString() {
        return "Resume{" + "name='" + name + '\'' + ", age=" + age + ", workExperience=" + workExperience + '}';
    }
}
