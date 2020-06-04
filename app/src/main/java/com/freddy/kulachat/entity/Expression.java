package com.freddy.kulachat.entity;

import java.io.Serializable;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/03 20:15
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class Expression implements Serializable {

    private int resId;// android用
    private String unique;// android text用，ios接收用

    public Expression(int resId, String unique) {
        this.resId = resId;
        this.unique = unique;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "resId=" + resId +
                ", unique='" + unique + '\'' +
                '}';
    }
}
