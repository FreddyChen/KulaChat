package com.freddy.kulachat.entity;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/27 16:33
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class TestModel {

    @JSONField(name = "name")
    private String name;
    @JSONField(name = "curPage")
    private int curPage;
    @JSONField(name = "offset")
    private int offset;
    @JSONField(name = "over")
    private boolean over;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
                "name='" + name + '\'' +
                ", curPage=" + curPage +
                ", offset=" + offset +
                ", over=" + over +
                '}';
    }
}
