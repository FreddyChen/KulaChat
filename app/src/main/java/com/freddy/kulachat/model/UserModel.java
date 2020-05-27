package com.freddy.kulachat.model;

import com.freddy.kulachat.contract.LoginContract;

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
}
