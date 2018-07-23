package com.springboot.girl.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Description 女孩属性
 * @Author GuanHuizhen
 * @Date 2018/7/20
 */
@ConfigurationProperties(prefix = "girl")
@Component
public class GirlProperty {

    private int age;
    private String cupSize;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }
}
