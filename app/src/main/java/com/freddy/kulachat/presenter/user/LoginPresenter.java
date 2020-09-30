package com.freddy.kulachat.presenter.user;

import com.freddy.kulachat.contract.user.LoginContract;
import com.freddy.kulachat.manager.CountdownManager;
import com.freddy.kulachat.manager.DelayManager;
import com.freddy.kulachat.model.user.UserModel;
import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.listener.OnNetResponseListener;
import com.freddy.kulachat.presenter.BasePresenter;

import java.util.concurrent.TimeUnit;

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

    @Inject
    public LoginPresenter() {

    }

    @Override
    public void getVerifyCode(String phone) {
        userModel.getVerifyCode(phone, new OnNetResponseListener<ResponseModel>() {
            @Override
            public void onStart() {
                if(isActive()) {
                    view.showGetVerifyCodeLoadingDialog();
                }
            }

            @Override
            public void onSucceed(ResponseModel responseModel) {
                if(isActive()) {
                    view.onGetVerifyCodeSucceed();
                }
            }

            @Override
            public void onFailed(int errCode, String errMsg) {
                super.onFailed(errCode, errMsg);
            }

            @Override
            public void onFinished() {
                if(isActive()) {
                    DelayManager.getInstance().startDelay(1, TimeUnit.SECONDS, () -> view.hideLoading());
                }
            }
        });
    }

    @Override
    public void login(String phone, String verifyCode) {
        userModel.login(phone, verifyCode, new OnNetResponseListener<ResponseModel>() {
            @Override
            public void onStart() {
                if(isActive()) {
                    view.showLoginLoadingDialog();
                }
            }

            @Override
            public void onSucceed(ResponseModel responseModel) {
                super.onSucceed(responseModel);
                if(isActive()) {
                    view.onLoginSucceed();
                }
            }

            @Override
            public void onFailed(int errCode, String errMsg) {
                super.onFailed(errCode, errMsg);
            }

            @Override
            public void onFinished() {
                if(isActive()) {
                    view.hideLoading();
                }
            }
        });
    }
}
