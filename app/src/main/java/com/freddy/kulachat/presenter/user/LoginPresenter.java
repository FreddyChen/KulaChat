package com.freddy.kulachat.presenter.user;

import com.freddy.kulachat.contract.user.LoginContract;
import com.freddy.kulachat.model.user.UserModel;
import com.freddy.kulachat.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/27 23:41
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Inject
    UserModel userModel;
}
