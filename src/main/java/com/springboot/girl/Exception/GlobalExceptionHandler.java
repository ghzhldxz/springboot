package com.springboot.girl.Exception;

import com.springboot.girl.bean.BizResult;
import com.springboot.girl.bean.CodeMsg;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description 全局异常处理器（异常切面）
 * @Author GuanHuizhen
 * @Date 2018/7/24
 */
//@ControllerAdvice
   @Aspect
   @Component
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /*@ExceptionHandler(Exception.class)
    @ResponseBody
    public BizResult<String> exceptionHandle(HttpServletRequest request, Exception e) {
        if(e instanceof GlobalException) {
            GlobalException ge = (GlobalException)e;
            logger.info(ge.getMessage(),ge);
            return BizResult.failure(ge.getCodeMsg());
        } else if(e instanceof BindException) {
            BindException be = (BindException)e;
            logger.info(be.getMessage(),be);
            List<ObjectError> errors = be.getAllErrors();//获取绑定异常中的错误列表
            ObjectError error = errors.get(0);//获取列表中第一个元素
            return BizResult.failure(CodeMsg.BIND_ERROR.filterMsg(error.getDefaultMessage()));//返回默认错误码信息
        } else {
            logger.info("服务异常",e);
            return BizResult.failure(CodeMsg.SERVER_ERROR);
        }
    }*/

    @Around("execution(* com.springboot.girl.controller.*.*(..)) && args(..,bindingResult)")
    public Object doAround(ProceedingJoinPoint pjp, BindingResult bindingResult) throws Throwable {
        Object retVal;
        if (bindingResult.hasErrors()) {
            retVal = doErrorHandle(bindingResult.getAllErrors());
        } else {
            retVal = pjp.proceed();
        }
        return retVal;

    }

    @AfterThrowing(value = "execution(* com.springboot.girl.controller.*.*(..)) ", throwing = "e")
    @ResponseBody
    public Object doException(JoinPoint joinPoint, Throwable e) {
        if(e instanceof GlobalException) {
            return BizResult.failure(CodeMsg.GLOBAL_ERROR);
        } else {
            return BizResult.failure(CodeMsg.SERVER_ERROR);
        }
    }

    private BizResult<String> doErrorHandle(List<ObjectError> errors) {
        return BizResult.failure(CodeMsg.BIND_ERROR.filterMsg(errors.get(0).getDefaultMessage()));
    }

}
