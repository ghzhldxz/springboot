package com.springboot.girl.design.strategy.impl;

import com.springboot.girl.design.strategy.IStrategy;

/**
 * @Description 满减
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class StrategyReturn implements IStrategy {
    private double money;
    private double returnMoney;

    public StrategyReturn(double money, double returnMoney) {
        this.money = money;
        this.returnMoney = returnMoney;
    }

    @Override
    public double getSettleMoney() {
        return money - returnMoney;
    }
}
