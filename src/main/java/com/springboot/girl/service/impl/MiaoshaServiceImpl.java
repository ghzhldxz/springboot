package com.springboot.girl.service.impl;

import com.springboot.girl.bean.*;
import com.springboot.girl.bean.vo.GoodsVo;
import com.springboot.girl.constants.OrderStatusEnum;
import com.springboot.girl.mapper.MiaoshaGoodsMapper;
import com.springboot.girl.mapper.MiaoshaOrderMapper;
import com.springboot.girl.mapper.OrderMapper;
import com.springboot.girl.service.GoodsService;
import com.springboot.girl.service.MiaoshaService;
import com.springboot.girl.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description 秒杀服务
 * @Author GuanHuizhen
 * @Date 2018/7/27
 */
@Service
public class MiaoshaServiceImpl implements MiaoshaService {

    @Autowired
    MiaoshaOrderMapper miaoshaOrderMapper;
    @Autowired
    MiaoshaGoodsMapper miaoshaGoodsMapper;
    @Autowired
    OrderService orderService;
    @Autowired
    GoodsService goodsService;

    /**
     * 秒杀下单
     * 生成秒杀订单和商品订单信息
     * @param user
     * @param goods
     * @return
     */
    @Transactional
    @Override
    public Order miaoshaGoods(User user, GoodsVo goods) {
        //减库存
        goodsService.reductMiaoshaGoodStock(goods);
        //生成订单
        Order order = orderService.createOrder(user,goods);

        //生成秒杀订单
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(order.getId());
        miaoshaOrderMapper.insertSelective(miaoshaOrder);

        return order;
    }

    /**
     * 根据用户id和商品id查询秒杀订单信息
     * @param userId
     * @param goodsId
     * @return
     */
    @Override
    public List<MiaoshaOrder> getMiaoshaOrderByUserIdGoodsId(Long userId, Long goodsId) {
        MiaoshaOrderExample example = new MiaoshaOrderExample();
        example.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(goodsId);
        return miaoshaOrderMapper.selectByExample(example);
    }

    @Override
    public MiaoshaGoods queryMiaoshaGoodsByGoodsId(Long goodsId) {
        MiaoshaGoodsExample example = new MiaoshaGoodsExample();
        example.createCriteria().andGoodsIdEqualTo(goodsId);
        return miaoshaGoodsMapper.selectByExample(example).get(0);
    }
}
