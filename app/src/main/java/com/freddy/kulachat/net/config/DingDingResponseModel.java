package com.freddy.kulachat.net.config;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/27 11:24
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class DingDingResponseModel {

    @JSONField(name = "errcode")
    private int code;
    @JSONField(name = "errmsg")
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

    @Override
    public String toString() {
        return "DingDingResponseModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
