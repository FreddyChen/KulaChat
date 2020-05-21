package com.freddy.kulachat;

import android.widget.TextView;

public class MainActivity extends BaseActivity<HomeContract.View, HomePresenter> implements HomeContract.View {

    private TextView tv;

    @Override
    public void onTest() {

    }

    @Override
    protected void setRootView() {
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        tv.postDelayed(() -> presenter.onPresenterTest(), 3000);
    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter();
    }
}
