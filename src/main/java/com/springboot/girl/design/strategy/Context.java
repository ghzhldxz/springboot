package com.springboot.girl.design.strategy;

/**
 * @Description 策略上下文 （发现怎么跟适配器模式：对象适配器好像呀，稍等改改就可以变成适配器模式）
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class Context {

    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    public double getResult() {
        return strategy.getSettleMoney();
    }
}
