package com.springboot.girl.controller;

import com.springboot.girl.bean.MiaoshaOrder;
import com.springboot.girl.bean.Order;
import com.springboot.girl.bean.User;
import com.springboot.girl.bean.vo.GoodsVo;
import com.springboot.girl.service.GoodService;
import com.springboot.girl.service.MiaoshaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description 秒杀
 * @Author GuanHuizhen
 * @Date 2018/7/27
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    Logger logger = LoggerFactory.getLogger(MiaoshaController.class);

    @Autowired
    GoodService goodService;
    @Autowired
    MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String toMiaosha(Model model, User user, Long goodsId) {
        if(user == null) {
            return "login";
        }
        GoodsVo goods = goodService.queryGoodVoByGoodsId(goodsId);
        if(goods.getStartDate().getTime()>System.currentTimeMillis()) {
            logger.info("秒杀时间还未开始");
            model.addAttribute("errmsg","秒杀时间还未开始");
            return "miaosha_fail";
        }
        if(goods.getEndDate().getTime()<System.currentTimeMillis()) {
            logger.info("您来晚啦，秒杀已结束");
            model.addAttribute("errmsg","您来晚啦，秒杀已结束");
            return "miaosha_fail";
        }
        //判断是否已经秒杀过了
        List<MiaoshaOrder> miaoshaOrderList = miaoshaService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
        if(miaoshaOrderList != null && miaoshaOrderList.size()>0) {
            logger.info("亲，您已秒杀过该商品~");
            model.addAttribute("errmsg","亲，您已秒杀过该商品~");
            return "miaosha_fail";
        }
        if(goods.getStockCount() <=0) {
            logger.info("秒杀商品已抢光~");
            model.addAttribute("errmsg","秒杀商品已抢光~");
            return "miaosha_fail";
        }

        //下单，减秒杀库存
        Order order = miaoshaService.miaoshaGoods(user,goods);
        order = new Order();
        order.setGoodsPrice(new BigDecimal(2.22));
        model.addAttribute("orderInfo",order);
        model.addAttribute("goods",goods);
        return "order_detail";
    }
}
