package com.springboot.girl.bean;

/**
 * @Description 前端返回结果
 * @Date 2018/7/23
 */
public class BizResult <T> {

    private int code;//错误码
    private String msg;//错误信息
    private T data;

    public BizResult(int code, String msg,T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static <T> BizResult<T> success(T data) {
        return new BizResult<T>(200,"SUCCESS",data);
    }

    public static <T> BizResult<T> failure(ResultCode resultCode) {
        return new BizResult<T>(resultCode.getCode(),resultCode.getMsg(),null);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
