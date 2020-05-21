package com.freddy.kulachat;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.freddy.kulachat.mvp.BasePresenter;
import com.freddy.kulachat.mvp.BaseView;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/22 02:01
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity implements BaseView {

    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRootView();
        presenter = getPresenter();
        presenter.attachView((V) this);
    }

    protected abstract void setRootView();

    protected abstract P getPresenter();

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoading(String content) {
        Log.d("freddy", "showLoading, content = " + content);
    }

    @Override
    public void hideLoading() {

    }
}
