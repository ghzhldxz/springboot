package com.springboot.girl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.girl.Exception.GlobalException;
import com.springboot.girl.bean.CodeMsg;
import com.springboot.girl.bean.User;
import com.springboot.girl.bean.vo.LoginVo;
import com.springboot.girl.mapper.UserMapper;
import com.springboot.girl.redis.BasePrefix;
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

    public User getById(long id) {
        User user = redisService.get(MiaoshaUserKey.userId,id+"",User.class);
        if(user!=null){
            return user;
        }
        user = userMapper.selectByPrimaryKey(id);
        if(user != null) {
            redisService.set(MiaoshaUserKey.userId,user.getId()+"",user);
        }
        return user;
    }

    public boolean updatePassword(String tokenId,long id,String passwordNew) {
        User user = this.getById(id);
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        User beUpdateUser = new User();
        beUpdateUser.setId(id);
        beUpdateUser.setPassword(MD5Util.formPassToDBPass(passwordNew,user.getSalt()));
        int ret = userMapper.updateByPrimaryKey(beUpdateUser);
        if(ret>0) {
            //清理用户信息缓存
            redisService.del(MiaoshaUserKey.userId,id+"");
            user.setPassword(beUpdateUser.getPassword());
            //更新缓存中cookie存储的值
            //我有一个疑问？为啥不用token对应的user信息，而要再缓存一个userid对应的user信息呢？这不是浪费空间吗？而且变更起来麻烦
            //而且还有一个问题，缓存的代码跟业务代码耦合度很高，维护起来不是很方便呀~
            redisService.set(MiaoshaUserKey.token,tokenId,user);
        }
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
