package com.springboot.girl.aop;

import com.springboot.girl.Exception.UserAuthException;
import com.springboot.girl.service.impl.UserServiceImpl;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description 用户登录校验AOP
 * @Author GuanHuizhen
 * @Date 2018/8/10
 */
@Aspect
@Component//没有这个注解，那么此切面就不会生效
public class UserValidator {

    @Pointcut("execution(* com.springboot.girl.controller.*.*(..))" +
     "&& !execution(* com.springboot.girl.controller.LoginController.*(..))")
    public void verify() {}

   /*  @Before("verify()")
    public void doVerify() throws Throwable {
         ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
         HttpServletRequest request = servletRequestAttributes.getRequest();
         Cookie[] cookies = request.getCookies();
         if (cookies==null) {
             throw new UserAuthException();
         }
         Cookie userCookie = null;
         for(Cookie cookie : cookies) {
             if(cookie.getName().equals(UserServiceImpl.COOKIE_NAME)) {
                 userCookie = cookie;
                 break;
             }
         }
         if(userCookie == null) {
             throw new UserAuthException();
         }
    }*/

}
