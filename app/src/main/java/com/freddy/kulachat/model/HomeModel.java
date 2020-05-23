package com.freddy.kulachat.model;

import android.util.Log;

import com.freddy.kulachat.contract.HomeContract;

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
        Log.d("HomeModel", "test()");
    }
}
