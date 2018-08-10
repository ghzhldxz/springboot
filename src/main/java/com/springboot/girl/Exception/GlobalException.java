package com.springboot.girl.Exception;

import com.springboot.girl.bean.CodeMsg;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description 全局异常
 * @Author GuanHuizhen
 * @Date 2018/7/24
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 7331557937300415546L;
    private CodeMsg codeMsg;

    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.getMsg());
        this.codeMsg = codeMsg;

    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
