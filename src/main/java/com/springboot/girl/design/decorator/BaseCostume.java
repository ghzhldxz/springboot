package com.springboot.girl.design.decorator;

/**
 * @Description 基本装饰
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class BaseCostume extends Person {
    Person compent;

    /**
     * 装饰
     * @param compent
     */
    public Person decorate(Person compent) {
        this.compent = compent;
        return this;
    }

    @Override
    public void show() {
        if(compent != null) {
            compent.show();
        }
    }
}
