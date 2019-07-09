package com.springboot.girl.design.prototype;

import lombok.Data;

/**
 * @Description TODO
 * @Author GuanHuizhen
 * @Date 2019/6/27
 */
@Data
public class WorkExperience implements Cloneable{
    private String unitName;
    private String workTime;

    public WorkExperience(String unitName, String workTime) {
        this.unitName = unitName;
        this.workTime = workTime;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "WorkExperience{" + "unitName='" + unitName + '\'' + ", workTime='" + workTime + '\'' + '}';
    }
}
