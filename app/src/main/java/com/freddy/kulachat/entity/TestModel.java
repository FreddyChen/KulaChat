package com.freddy.kulachat.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/25 00:21
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class TestModel {

    @JSONField(name = "curPage")
    private int curPage;
    @JSONField(name = "offset")
    private int offset;
    @JSONField(name = "over")
    private boolean over;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "curPage=" + curPage +
                ", offset=" + offset +
                ", over=" + over +
                '}';
    }
}
