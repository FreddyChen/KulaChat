package com.freddy.kulachat.model.home;

import com.freddy.kulachat.contract.home.HomeContract;
import com.freddy.kulachat.model.BaseModel;
import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.listener.OnNetResponseListener;

import javax.inject.Inject;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 18:56
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class HomeModel extends BaseModel implements HomeContract.Model {

    @Inject
    public HomeModel() {

    }

    @Override
    public void test() {
        request("test.action", new OnNetResponseListener<ResponseModel>() {
            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onSucceed(ResponseModel responseModel) {
                super.onSucceed(responseModel);
            }

            @Override
            public void onFailed(int errCode, String errMsg) {
                super.onFailed(errCode, errMsg);
            }

            @Override
            public void onFinished() {
                super.onFinished();
            }
        });
    }
}
