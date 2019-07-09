package com.springboot.girl.design.factory2;

/**
 * @Description 产品A的子工厂
 * @Author GuanHuizhen
 * @Date 2019/6/27
 */
public class ProductAFactory implements IFactory {
    @Override
    public IProduct getProduct() {
        return new ProductA();
    }
}
