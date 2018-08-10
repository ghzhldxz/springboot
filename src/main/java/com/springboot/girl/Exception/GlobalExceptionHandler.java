package com.springboot.girl.Exception;

import com.springboot.girl.bean.BizResult;
import com.springboot.girl.bean.CodeMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description 全局异常处理器（异常切面）
 * @Author GuanHuizhen
 * @Date 2018/7/24
 */
@ControllerAdvice
  // @Aspect
   //@Component
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({GlobalException.class,BindException.class})
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
    }

    @ExceptionHandler({UserAuthException.class})
    //@ResponseStatus
    public ModelAndView checkUserAuthHandle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new ModelAndView("redirect:"+request.getContextPath()+"/login/to_login");
    }

 }
