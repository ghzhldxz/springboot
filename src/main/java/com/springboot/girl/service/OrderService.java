package com.springboot.girl.service;

import com.springboot.girl.bean.Order;
import com.springboot.girl.bean.User;
import com.springboot.girl.bean.vo.GoodsVo;

public interface OrderService {
    Order createOrder(User user, GoodsVo goodsVo);
}
