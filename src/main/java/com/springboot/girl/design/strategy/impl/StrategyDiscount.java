package com.springboot.girl.design.strategy.impl;

import com.springboot.girl.design.strategy.IStrategy;

/**
 * @Description 打折
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class StrategyDiscount implements IStrategy {

    private double money;
    private double discount;

    public StrategyDiscount(double money, double discount) {
        this.money = money;
        this.discount = discount;
    }

    @Override
    public double getSettleMoney() {
        return money * discount / 10;
    }
}
