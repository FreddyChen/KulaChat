package com.freddy.kulachat.presenter.user;

import com.freddy.kulachat.KulaApp;
import com.freddy.kulachat.contract.user.CompleteInfoContract;
import com.freddy.kulachat.entity.User;
import com.freddy.kulachat.model.user.UserManager;
import com.freddy.kulachat.model.user.UserModel;
import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.listener.OnNetResponseListener;
import com.freddy.kulachat.presenter.BasePresenter;

import javax.inject.Inject;

public class CompleteInfoPresenter extends BasePresenter<CompleteInfoContract.View> implements CompleteInfoContract.Presenter {

    @Inject
    UserModel userModel;

    @Inject
    public CompleteInfoPresenter() {

    }

    @Override
    public void completeInfo(String avatar, String nickname, int gender, String birthday, String province, String city, String area, String signature) {
        userModel.completeInfo(avatar, nickname, gender, birthday, province, city, area, signature, new OnNetResponseListener<ResponseModel>() {

            @Override
            public void onStart() {
                if (isActive()) {
                    view.showCompleteInfoLoadingDialog();
                }
            }

            @Override
            public void onSucceed(ResponseModel responseModel) {
                User currentUser = UserManager.getInstance().getCurrentUser();
                currentUser.setAvatar(avatar);
                currentUser.setNickname(nickname);
                currentUser.setGender(gender);
                currentUser.setBirthday(birthday);
                currentUser.setProvince(province);
                currentUser.setCity(city);
                currentUser.setArea(area);
                currentUser.setSignature(signature);
                currentUser.setCompletedInfo(true);
                UserManager.getInstance().setCurrentUser(currentUser);
                KulaApp.getInstance().connectIMS();
                if (isActive()) {
                    view.onCompleteInfoSucceed();
                }
            }

            @Override
            public void onFinished() {
                if (isActive()) {
                    view.hideLoading();
                }
            }
        });
    }
}
