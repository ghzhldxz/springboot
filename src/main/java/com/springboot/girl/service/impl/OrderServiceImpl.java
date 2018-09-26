package com.springboot.girl.service.impl;

import com.springboot.girl.bean.Order;
import com.springboot.girl.bean.User;
import com.springboot.girl.bean.vo.GoodsVo;
import com.springboot.girl.constants.OrderStatusEnum;
import com.springboot.girl.mapper.OrderMapper;
import com.springboot.girl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 订单服务
 * @Author GuanHuizhen
 * @Date 2018/7/27
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderMapper orderMapper;

    @Override
    public Order createOrder(User user, GoodsVo goodsVo) {
        Order order = new Order();
        order.setGoodsId(goodsVo.getId());
        order.setGoodsCount(1);
        order.setGoodsName(goodsVo.getGoodsName());
        order.setGoodsPrice(goodsVo.getMiaoshaPrice());
        order.setStatus(OrderStatusEnum.CREATE.getKey());
        order.setUserId(user.getId());
        Long orderId = orderMapper.insertSelective(order);
        order.setId(orderId);
        return order;
    }
}
