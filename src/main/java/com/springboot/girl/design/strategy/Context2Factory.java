package com.springboot.girl.design.strategy;

import com.springboot.girl.design.strategy.impl.StrategyDiscount;
import com.springboot.girl.design.strategy.impl.StrategyReturn;

/**
 * @Description 策略上下文
 * @Author GuanHuizhen
 * @Date 2019/6/24
 */
public class Context2Factory {

    private IStrategy strategy;

    public Context2Factory(int type,double money, double a) {
        switch (type) {
            case 0 :
                strategy = new StrategyReturn(money,a);
                break;
            case 1 :
                strategy = new StrategyDiscount(money,a);
                break;
        }
    }

    public double getResult() {
        return strategy.getSettleMoney();
    }
}
