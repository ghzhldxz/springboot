package com.springboot.girl.bean.vo;

import com.springboot.girl.bean.User;
import lombok.Data;

/**
 * @Description 商品详情
 * @Author GuanHuizhen
 * @Date 2018/8/21
 */
@Data
public class GoodsDetailVo {
    private GoodsVo goods;
    private User user;
    private String miaoshaStatus;
    private String remainSeconds;
    private String endingSeconds;
}
