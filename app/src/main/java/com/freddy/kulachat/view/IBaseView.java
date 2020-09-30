package com.freddy.kulachat.view;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 15:39
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public interface IBaseView {

    void showLoadingDialog();

    void showLoadingDialog(String title);

    void showLoadingDialog(boolean cancelable, boolean canceledOnTouchOutside);

    void showLoadingDialog(String title, boolean cancelable, boolean canceledOnTouchOutside);

    void hideLoading();
}
