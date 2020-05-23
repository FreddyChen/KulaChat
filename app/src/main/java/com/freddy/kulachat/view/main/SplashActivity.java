package com.freddy.kulachat.view.main;

import android.os.Bundle;

import com.freddy.kulachat.R;
import com.freddy.kulachat.presenter.NullablePresenter;
import com.freddy.kulachat.view.BaseActivity;
import com.jaeger.library.StatusBarUtil;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/24 01:52
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class SplashActivity extends BaseActivity<NullablePresenter> {

    @Override
    protected void setRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void setStatusBarColor() {
        StatusBarUtil.setTransparent(this);
    }
}
