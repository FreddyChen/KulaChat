package com.freddy.kulachat.view.main;

import android.os.Bundle;
import com.freddy.kulachat.presenter.NullablePresenter;
import com.freddy.kulachat.view.BaseActivity;

public class MainActivity extends BaseActivity<NullablePresenter> {

    @Override
    protected void setRootView(Bundle savedInstanceState) {
        startActivity(SplashActivity.class);
        finish();
    }
}
