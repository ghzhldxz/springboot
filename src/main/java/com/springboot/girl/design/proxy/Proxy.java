package com.springboot.girl.design.proxy;

/**
 * @Description 香港代购
 * @Author GuanHuizhen
 * @Date 2019/6/26
 */
public class Proxy implements IBuy{

    IBuy buyer;

    public Proxy(IBuy buyer) {
        this.buyer = buyer;
    }

    @Override
    public Apple buyApple() {
        buyer.buyApple();
        Seller seller = new Seller();
        return seller.createOrder();
    }
}
