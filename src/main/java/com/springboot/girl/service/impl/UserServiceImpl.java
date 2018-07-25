package com.springboot.girl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.girl.Exception.GlobalException;
import com.springboot.girl.bean.CodeMsg;
import com.springboot.girl.bean.User;
import com.springboot.girl.bean.vo.LoginVo;
import com.springboot.girl.mapper.UserMapper;
import com.springboot.girl.redis.MiaoshaUserKey;
import com.springboot.girl.redis.RedisService;
import com.springboot.girl.service.UserService;
import com.springboot.girl.util.MD5Util;
import com.springboot.girl.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 用户登录
 * @Author GuanHuizhen
 * @Date 2018/7/25
 */
@Service("userService")
public class UserServiceImpl implements UserService{
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserMapper userMapper;
    @Autowired
    RedisService redisService;

    public static final String COOKIE_NAME = "token";

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        logger.info("beign>>>>>>>>>>login");
        if(loginVo == null ) {
            throw new GlobalException(CodeMsg.GLOBAL_ERROR);
        }
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        User user = userMapper.selectByPrimaryKey(Long.valueOf(mobile));
        if(user == null) {
            throw new GlobalException(CodeMsg.USER_EMPTY);
        }
        if(!user.getPassword().equals(MD5Util.formPassToDBPass(password,user.getSalt()))){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String tokenId = UUIDUtil.uuid();
        //将用户信息保存到redis中和cookie中
        addCookie(response,tokenId,user);

        logger.info("end>>>>>>>>>>login");
        return true;
    }

    @Override
    public User getUserByToken(HttpServletResponse response,String tokenId) {
        if(StringUtils.isEmpty(tokenId)) {
            return null;
        }
        User user = redisService.get(MiaoshaUserKey.token,tokenId,User.class);
        if(user != null) {
            addCookie(response,tokenId,user);
        }
        return user;
    }

    private void addCookie(HttpServletResponse response,String tokenId,User user) {
        redisService.set(MiaoshaUserKey.token,tokenId, user);
        Cookie cookie = new Cookie(COOKIE_NAME,tokenId);
        cookie.setMaxAge(MiaoshaUserKey.TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
