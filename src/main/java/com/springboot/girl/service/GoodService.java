package com.springboot.girl.service;

import com.springboot.girl.bean.User;
import com.springboot.girl.bean.vo.GoodsVo;

import java.util.List;

public interface GoodService {

    public List<GoodsVo> queryGoodVoList();

    public GoodsVo queryGoodVoByGoodsId(Long goodsId);
}
