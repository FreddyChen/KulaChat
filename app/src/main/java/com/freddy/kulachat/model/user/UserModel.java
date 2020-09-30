package com.freddy.kulachat.model.user;

import com.freddy.kulachat.contract.user.LoginContract;
import com.freddy.kulachat.model.BaseModel;
import com.freddy.kulachat.net.config.NetworkConfig;
import com.freddy.kulachat.net.config.RequestMethod;
import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.listener.OnNetResponseListener;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/27 23:37
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class UserModel extends BaseModel implements LoginContract.Model {

    @Inject
    public UserModel() {

    }

    @Override
    public void getVerifyCode(String phone, OnNetResponseListener<ResponseModel> listener) {
        Map<String, Object> params = new HashMap<>();
        params.put(NetworkConfig.PARAM_USER_PHONE, phone);
        request(NetworkConfig.FUNC_USER_GET_VERIFY_CODE, params, listener);
    }

    @Override
    public void login(String phone, String verifyCode, OnNetResponseListener<ResponseModel> listener) {
        Map<String, Object> params = new HashMap<>();
        params.put(NetworkConfig.PARAM_USER_PHONE, phone);
        params.put(NetworkConfig.PARAM_USER_VERIFY_CODE, verifyCode);
        request(NetworkConfig.FUNC_USER_LOGIN, params, listener);
    }
}
