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

    private int typeResId;
    private List<Expression> expressionList;

    public ExpressionType(int typeResId, List<Expression> expressionList) {
        this.typeResId = typeResId;
        this.expressionList = expressionList;
    }

    public int getTypeResId() {
        return typeResId;
    }

    public void setTypeResId(int typeResId) {
        this.typeResId = typeResId;
    }

    public List<Expression> getExpressionList() {
        return expressionList;
    }

    public void setExpressionList(List<Expression> expressionList) {
        this.expressionList = expressionList;
    }
}
