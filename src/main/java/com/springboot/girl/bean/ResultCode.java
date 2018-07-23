package com.springboot.girl.bean;

/**
 * @Description 返回码和返回信息
 * @Date 2018/7/23
 */
public class ResultCode {
    private int code;
    private String msg;

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
}
