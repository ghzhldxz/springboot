package com.springboot.girl.redis;

/**
 * @Description 商品缓存设置
 * @Author GuanHuizhen
 * @Date 2018/8/20
 */
public class GoodsKey extends BasePrefix{
    private GoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }

    public static GoodsKey goodsList = new GoodsKey(60,"gl");
    public static GoodsKey goodsDetail = new GoodsKey(60,"gd");
}
