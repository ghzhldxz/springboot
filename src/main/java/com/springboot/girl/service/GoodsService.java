package com.springboot.girl.service;

import com.springboot.girl.bean.User;
import com.springboot.girl.bean.vo.GoodsVo;

import java.util.List;

public interface GoodsService {

    public List<GoodsVo> queryGoodVoList();

    public GoodsVo queryGoodVoByGoodsId(Long goodsId);

    void reductMiaoshaGoodStock(GoodsVo goods);
}
