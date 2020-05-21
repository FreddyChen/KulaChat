package com.freddy.kulachat.mvp;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/22 01:37
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public interface BaseView {

    void showLoading();

    void showLoading(String content);

    void hideLoading();
}
