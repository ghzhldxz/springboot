package com.springboot.girl.service;

import com.springboot.girl.Exception.GlobalException;
import com.springboot.girl.bean.CodeMsg;
import com.springboot.girl.bean.User;
import com.springboot.girl.bean.UserExample;
import com.springboot.girl.bean.vo.LoginVo;
import com.springboot.girl.mapper.UserMapper;
import com.springboot.girl.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface UserService {

    public boolean login(HttpServletResponse response, LoginVo loginVo) ;

    public User getUserByToken(HttpServletResponse response,String tokenId);
}
