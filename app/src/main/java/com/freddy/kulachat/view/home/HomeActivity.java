package com.freddy.kulachat.view.home;

import android.os.Bundle;
import android.util.Log;

import com.freddy.kulachat.R;
import com.freddy.kulachat.contract.HomeContract;
import com.freddy.kulachat.presenter.HomePresenter;
import com.freddy.kulachat.view.BaseActivity;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 18:52
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class HomeActivity extends BaseActivity<HomePresenter> implements HomeContract.View {

    @Override
    protected void setRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void start() {
        presenter.test();
    }

    @Override
    public void onTestSuccess() {
        Log.d("HomeActivity", "onTestSuccess()");
    }
}
