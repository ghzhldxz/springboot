package com.springboot.girl.controller;

import com.springboot.girl.bean.User;
import com.springboot.girl.redis.MiaoshaUserKey;
import com.springboot.girl.redis.RedisService;
import com.springboot.girl.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;
	
    @RequestMapping("/to_list")
    public String list(Model model,HttpServletResponse response,
					  // @CookieValue(value = "token",required = false) String cookieValue,//两个参数只要上送任意一个就行，所以要设置为非必需
					  // @RequestParam(value = "token",required = false) String paramCookieValue
	User user){
    	//model.addAttribute("user", user);
//		if(StringUtils.isEmpty(cookieValue) && StringUtils.isEmpty(paramCookieValue)) {
//			return "login";
//		}
//		String tokenId = StringUtils.isEmpty(paramCookieValue) ? cookieValue : paramCookieValue;
//		User user = userService.getUserByToken(response,tokenId);
		model.addAttribute("user",user);
        return "goods_list";
    }
    
}
