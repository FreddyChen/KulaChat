package com.freddy.kulachat.net.config;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/24 21:56
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class ResponseModel<T> {

    @JSONField(name = "errorCode")
    private int code;
    @JSONField(name = "errorMsg")
    private String msg;
    @JSONField(name = "data")
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
