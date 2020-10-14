package com.freddy.kulachat.contract.user;

import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.listener.OnNetResponseListener;
import com.freddy.kulachat.view.IBaseView;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/27 23:36
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public interface LoginContract {

    interface Model {

        /**
         * 获取验证码
         * @param phone
         * @param listener
         */
        void getVerifyCode(String phone, OnNetResponseListener<ResponseModel> listener);

        /**
         * 用户登录
         * @param phone
         * @param verifyCode
         * @param listener
         */
        void login(String phone, String verifyCode, OnNetResponseListener<ResponseModel> listener);
    }

    interface View extends IBaseView {
        void showGetVerifyCodeLoadingDialog();
        void showLoginLoadingDialog();
        void onGetVerifyCodeSucceed();
        void onLoginSucceed(boolean isCompletedInfo);
    }

    interface Presenter {
        void getVerifyCode(String phone);
        void login(String phone, String verifyCode);
    }
}
