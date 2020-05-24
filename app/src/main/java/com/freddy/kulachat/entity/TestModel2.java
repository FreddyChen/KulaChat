package com.freddy.kulachat.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/25 02:47
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class TestModel2 {

    @JSONField(name = "id")
    private int id;
    @JSONField(name = "name")
    private String name;
    @JSONField(name = "userControlSetTop")
    private boolean userControlSetTop;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    @Override
    public String toString() {
        return "TestModel2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userControlSetTop=" + userControlSetTop +
                '}';
    }
}
