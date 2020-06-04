package com.freddy.kulachat.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.freddy.kulachat.presenter.NullablePresenter;
import com.freddy.kulachat.utils.DensityUtil;
import com.freddy.kulachat.view.BaseActivity;

public class MainActivity extends BaseActivity<NullablePresenter> {

    @Override
    protected void setRootView(Bundle savedInstanceState) {
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
//        DisplayMetrics metrics = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
//        DensityUtil.metrics = metrics;

        startActivity(SplashActivity.class);
        finish();
    }
}
