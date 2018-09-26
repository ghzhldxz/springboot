package com.springboot.girl.service.impl;

import com.springboot.girl.bean.Goods;
import com.springboot.girl.bean.MiaoshaGoods;
import com.springboot.girl.bean.vo.GoodsVo;
import com.springboot.girl.mapper.GoodsMapper;
import com.springboot.girl.mapper.MiaoshaGoodsMapper;
import com.springboot.girl.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 商品信息
 * @Author GuanHuizhen
 * @Date 2018/7/25
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    MiaoshaGoodsMapper miaoshaGoodsMapper;

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

    @Override
    public void reductMiaoshaGoodStock(GoodsVo goods) {
        MiaoshaGoods miaoshaGoods = new MiaoshaGoods();
        miaoshaGoods.setGoodsId(goods.getId());
        miaoshaGoodsMapper.updateMiaoshaGoodStock(miaoshaGoods);
    }
}
