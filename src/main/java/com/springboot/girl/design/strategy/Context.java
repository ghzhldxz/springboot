package com.springboot.girl.design.strategy;

/**
 * @Description 策略上下文
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
