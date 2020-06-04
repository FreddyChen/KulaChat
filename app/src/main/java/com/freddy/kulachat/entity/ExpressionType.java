package com.freddy.kulachat.entity;

import java.util.List;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/03 20:16
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class ExpressionType {

    private int resId;
    private List<Expression> expressionList;

    public ExpressionType(int resId, List<Expression> expressionList) {
        this.resId = resId;
        this.expressionList = expressionList;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public List<Expression> getExpressionList() {
        return expressionList;
    }

    public void setExpressionList(List<Expression> expressionList) {
        this.expressionList = expressionList;
    }

    @Override
    public String toString() {
        return "ExpressionType{" +
                "typeResId=" + resId +
                ", expressionList=" + expressionList +
                '}';
    }
}
