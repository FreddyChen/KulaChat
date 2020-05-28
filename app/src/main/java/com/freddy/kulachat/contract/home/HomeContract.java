package com.freddy.kulachat.contract.home;

import com.freddy.kulachat.view.IBaseView;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 18:53
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public interface HomeContract {

    interface Model {
        void test();
    }

    interface View extends IBaseView {
        void onTestSuccess();
    }

    interface Presenter {
        void test();
    }
}
