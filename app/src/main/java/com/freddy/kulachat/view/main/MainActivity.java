package com.freddy.kulachat.view.main;

import android.os.Bundle;
import android.util.Log;

import com.freddy.kulachat.R;
import com.freddy.kulachat.presenter.NullablePresenter;
import com.freddy.kulachat.view.BaseActivity;
import com.freddy.kulachat.view.home.HomeActivity;
import com.google.gson.Gson;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<NullablePresenter> {

    @Inject
    Gson gson;

    @Inject
    Gson gson2;

    @Override
    protected void setRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        Log.d("MainActivity", "gson = " + gson.hashCode() + "\tgson2 = " + gson2.hashCode());
        Log.d("MainActivity", "presenter = " + presenter);

        startActivity(HomeActivity.class);
        finish();
    }
}
