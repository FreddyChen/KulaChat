package com.freddy.kulachat;

import com.freddy.kulachat.mvp.BasePresenter;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/22 02:05
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Override
    public void onPresenterTest() {
        if(isViewAttached()) {
            view.showLoading("testtttt");
        }
    }
}
