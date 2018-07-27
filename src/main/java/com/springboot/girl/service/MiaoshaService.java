package com.springboot.girl.service;

import com.springboot.girl.bean.MiaoshaOrder;
import com.springboot.girl.bean.Order;
import com.springboot.girl.bean.User;
import com.springboot.girl.bean.vo.GoodsVo;

import java.util.List;

public interface MiaoshaService {
    public Order miaoshaGoods(User user, GoodsVo goods);

    public List<MiaoshaOrder> getMiaoshaOrderByUserIdGoodsId(Long id, Long goodsId);
}
