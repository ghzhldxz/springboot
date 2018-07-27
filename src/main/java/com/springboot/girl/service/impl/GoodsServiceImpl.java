package com.springboot.girl.service.impl;

import com.springboot.girl.bean.User;
import com.springboot.girl.bean.vo.GoodsVo;
import com.springboot.girl.mapper.GoodsMapper;
import com.springboot.girl.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 商品信息
 * @Author GuanHuizhen
 * @Date 2018/7/25
 */
@Service
public class GoodsServiceImpl implements GoodService {
    @Autowired
    GoodsMapper goodsMapper;

    /**
     * 查询秒杀商品列表
     * @return
     */
    @Override
    public List<GoodsVo> queryGoodVoList() {
        List<GoodsVo> goodsList = goodsMapper.selectGoodVoBySelective(null);
        return goodsList;
    }

    /**
     * 根据商品id获取秒杀商品详细信息
     * @param goodsId
     * @return
     */
    @Override
    public GoodsVo queryGoodVoByGoodsId(Long goodsId) {
        List<GoodsVo> goodsList = goodsMapper.selectGoodVoBySelective(goodsId);
        if(goodsList == null || goodsList.isEmpty()) {
            return null;
        }
        return goodsList.get(0);
    }
}
