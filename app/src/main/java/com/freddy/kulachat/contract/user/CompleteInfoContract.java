package com.freddy.kulachat.contract.user;

import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.listener.OnNetResponseListener;
import com.freddy.kulachat.view.IBaseView;

public interface CompleteInfoContract {

    interface Model {
        void completeInfo(String avatar, String nickname, int gender, String birthday, String province, String city, String area, String signature, OnNetResponseListener<ResponseModel> listener);
    }

    interface View extends IBaseView {
        void showCompleteInfoLoadingDialog();
        void onCompleteInfoSucceed();
    }

    interface Presenter {
        void completeInfo(String avatar, String nickname, int gender, String birthday, String province, String city, String area, String signature);
    }
}
