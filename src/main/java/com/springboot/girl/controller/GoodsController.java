package com.springboot.girl.controller;

import com.springboot.girl.bean.MiaoshaOrder;
import com.springboot.girl.bean.Order;
import com.springboot.girl.bean.User;
import com.springboot.girl.bean.vo.GoodsVo;
import com.springboot.girl.redis.MiaoshaUserKey;
import com.springboot.girl.redis.RedisService;
import com.springboot.girl.service.GoodService;
import com.springboot.girl.service.MiaoshaService;
import com.springboot.girl.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	Logger logger = LoggerFactory.getLogger(GoodsController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;
	@Autowired
	GoodService goodService;

	@Autowired
	MiaoshaService miaoshaService;
	
    @RequestMapping("/to_list")
    public String list(Model model,HttpServletResponse response,
					  // @CookieValue(value = "token",required = false) String cookieValue,//两个参数只要上送任意一个就行，所以要设置为非必需
					  // @RequestParam(value = "token",required = false) String paramCookieValue
	User user){
    	if(user == null) {
    		return  "login";
		}
    	//model.addAttribute("user", user);
//		if(StringUtils.isEmpty(cookieValue) && StringUtils.isEmpty(paramCookieValue)) {
//			return "login";
//		}
//		String tokenId = StringUtils.isEmpty(paramCookieValue) ? cookieValue : paramCookieValue;
//		User user = userService.getUserByToken(response,tokenId);
		List<GoodsVo> goodsList = goodService.queryGoodVoList();
		model.addAttribute("user",user);
		model.addAttribute("goodsList",goodsList);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
	public String toDetail(@PathVariable("goodsId") Long goodsId, Model model) {
    	GoodsVo goods = goodService.queryGoodVoByGoodsId(goodsId);
    	int miaoshaStatus = 0;
    	long startTime = goods.getStartDate().getTime();
    	long endTime = goods.getEndDate().getTime();
    	long currentTime = System.currentTimeMillis();
		long remainSeconds = 0;
		long endingSeconds = (endTime-currentTime)/1000;
		if(startTime>currentTime) {//秒杀还没开始，倒计时ing
			remainSeconds = (startTime - currentTime)/1000;
		} else if(endTime<currentTime) {//秒杀已结束
			miaoshaStatus = 2;
		} else {//秒杀进行时
			miaoshaStatus = 1;
		}

    	model.addAttribute("goods",goods);
    	model.addAttribute("miaoshaStatus",miaoshaStatus);
    	model.addAttribute("remainSeconds",remainSeconds);
    	model.addAttribute("endingSeconds",endingSeconds);
		return "goods_detail";
	}

}
