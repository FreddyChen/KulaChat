package com.freddy.kulachat.presenter.home;

import com.freddy.kulachat.contract.home.HomeContract;
import com.freddy.kulachat.model.home.HomeModel;
import com.freddy.kulachat.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 18:54
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    @Inject
    HomeModel homeModel;

    @Inject
    public HomePresenter(HomeModel homeModel) {
        this.homeModel = homeModel;
    }

    @Override
    public void test() {
        homeModel.test();
        if(isActive()) {
            view.onTestSuccess();
        }
    }
}
