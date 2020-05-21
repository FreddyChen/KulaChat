package com.freddy.kulachat;

import com.freddy.kulachat.mvp.BaseView;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/22 01:54
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public interface HomeContract {

    interface View extends BaseView {
        void onTest();
    }

    interface Presenter {
        void onPresenterTest();
    }
}
