package com.springboot.girl.controller;

import com.springboot.girl.bean.BizResult;
import com.springboot.girl.bean.User;
import com.springboot.girl.bean.vo.GoodsDetailVo;
import com.springboot.girl.bean.vo.GoodsVo;
import com.springboot.girl.redis.GoodsKey;
import com.springboot.girl.redis.RedisService;
import com.springboot.girl.service.GoodsService;
import com.springboot.girl.service.MiaoshaService;
import com.springboot.girl.service.UserService;
import com.springboot.girl.util.SpringWebContextUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	GoodsService goodService;

	@Autowired
	MiaoshaService miaoshaService;

	@Autowired
	ThymeleafViewResolver thymeleafViewResolver;
	@Autowired
	ApplicationContext applicationContext;

	/*@RequestMapping("/to_list")
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
		//将good_list页面写到缓存 中，那么就需要手动渲染页面
		List<GoodsVo> goodsList = goodService.queryGoodVoList();
		model.addAttribute("user",user);
		model.addAttribute("goodsList",goodsList);
        return "goods_list";
    }*/

	/**
	 * 手动解析缓存页面中th模板的标签内容
	 * @param model
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/to_list",produces = "text/html")
	@ResponseBody
	public String list(Model model,HttpServletResponse response,HttpServletRequest request,
					   // @CookieValue(value = "token",required = false) String cookieValue,//两个参数只要上送任意一个就行，所以要设置为非必需
					   // @RequestParam(value = "token",required = false) String paramCookieValue
					   User user){
		if(user == null) {
			return  "login";
		}
		String html = redisService.get(GoodsKey.goodsList,"",String.class);
		if(!StringUtils.isEmpty(html)) {
			return html;
		}
		//将good_list页面写到缓存 中，那么就需要手动渲染页面
		List<GoodsVo> goodsList = goodService.queryGoodVoList();
		model.addAttribute("user",user);
		model.addAttribute("goodsList",goodsList);

		SpringWebContextUtil ctx = new SpringWebContextUtil(request,response,
				request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
		//手动渲染
		html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
		if(!StringUtils.isEmpty(html)) {
			redisService.set(GoodsKey.goodsList,"",html);
		}
		return html;
	}

   /* @RequestMapping("/to_detail/{goodsId}")
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
	}*/

	@RequestMapping("/detail/{goodsId}")
	@ResponseBody
	public BizResult<GoodsDetailVo> toDetail(@PathVariable("goodsId") Long goodsId, Model model,User user) {
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
		GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
		goodsDetailVo.setGoods(goods);
		goodsDetailVo.setUser(user);
		goodsDetailVo.setMiaoshaStatus(miaoshaStatus+"");
		goodsDetailVo.setRemainSeconds(remainSeconds+"");
		goodsDetailVo.setEndingSeconds(endingSeconds+"");
		return BizResult.success(goodsDetailVo);
	}

}
