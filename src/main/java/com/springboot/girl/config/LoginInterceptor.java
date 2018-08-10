package com.springboot.girl.config;

import com.springboot.girl.service.impl.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 用户登录拦截器
 * @Author GuanHuizhen
 * @Date 2018/8/10
 */
@Service
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        if(path.indexOf("login")>0) {
            return true;
        }
        Cookie[]  cookies = request.getCookies();
        if(cookies == null) {
            response.sendRedirect(request.getContextPath()+"/login/to_login");
            return false;
        }
        Cookie userCookie = null;
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(UserServiceImpl.COOKIE_NAME)) {
                userCookie = cookie;
                break;
            }
        }
        if(userCookie == null || StringUtils.isEmpty(userCookie.getValue())) {
            response.sendRedirect(request.getContextPath()+"/login/to_login");
            return false;
        }
        return true;
    }
}
