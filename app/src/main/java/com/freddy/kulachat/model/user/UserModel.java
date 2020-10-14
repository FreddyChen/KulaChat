package com.freddy.kulachat.model.user;

import com.freddy.kulachat.contract.user.CompleteInfoContract;
import com.freddy.kulachat.contract.user.LoginContract;
import com.freddy.kulachat.entity.User;
import com.freddy.kulachat.model.BaseModel;
import com.freddy.kulachat.net.config.NetworkConfig;
import com.freddy.kulachat.net.config.RequestMethod;
import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.listener.OnNetResponseListener;
import com.freddy.kulachat.utils.StringUtil;

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
public class UserModel extends BaseModel implements LoginContract.Model, CompleteInfoContract.Model {

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

    @Override
    public void completeInfo(String avatar, String nickname, int gender, String birthday, String province, String city, String area, String signature, OnNetResponseListener<ResponseModel> listener) {
        Map<String, Object> params = new HashMap<>();
        if (StringUtil.isNotEmpty(avatar)) {
            params.put(NetworkConfig.PARAM_USER_AVATAR, avatar);
        }
        if (StringUtil.isNotEmpty(nickname)) {
            params.put(NetworkConfig.PARAM_USER_NICKNAME, nickname);
        }
        if (gender != User.GENDER_UNKNOWN) {
            params.put(NetworkConfig.PARAM_USER_GENDER, gender);
        }
        if (StringUtil.isNotEmpty(birthday)) {
            params.put(NetworkConfig.PARAM_USER_BIRTHDAY, birthday);
        }
        if (StringUtil.isNotEmpty(province)) {
            params.put(NetworkConfig.PARAM_USER_PROVINCE, province);
        }
        if (StringUtil.isNotEmpty(city)) {
            params.put(NetworkConfig.PARAM_USER_CITY, city);
        }
        if (StringUtil.isNotEmpty(area)) {
            params.put(NetworkConfig.PARAM_USER_AREA, area);
        }
        if (StringUtil.isNotEmpty(signature)) {
            params.put(NetworkConfig.PARAM_USER_SIGNATURE, signature);
        }
        request(NetworkConfig.FUNC_USER_COMPLETE_INFO, params, listener);
    }
}
