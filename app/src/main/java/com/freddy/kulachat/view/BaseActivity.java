package com.freddy.kulachat.view;

import android.content.Intent;
import android.os.Bundle;

import com.freddy.kulachat.presenter.BasePresenter;
import com.freddy.kulachat.widget.CLoadingDialog;
import com.jaeger.library.StatusBarUtil;

import java.io.Serializable;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 18:41
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView {

    @Inject
    public P presenter;

    private Unbinder unbinder;

    protected BaseActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            AndroidInjection.inject(this);
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        activity = this;
        CActivityManager.getInstance().addActivityToStack(activity);
        setRootView(savedInstanceState);
        setStatusBarColor();
        unbinder = ButterKnife.bind(this);
        init();
        setListeners();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.attachView(this);
        }

        start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        restart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        stop();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
        }

        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }

        destroy();
        super.onDestroy();
        CActivityManager.getInstance().removeActivityFromStack(activity);
    }

    @Override
    public void showLoading() {
        this.showLoading("加载中");
    }

    private CLoadingDialog mLoadingDialog;
    @Override
    public void showLoading(String title) {
        mLoadingDialog = new CLoadingDialog.Builder()
                .setTitle(title)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .build();
        mLoadingDialog.show(getSupportFragmentManager(), null);
    }

    @Override
    public void hideLoading() {
        if(mLoadingDialog != null && mLoadingDialog.isVisible()) {
            mLoadingDialog.dismiss();
        }
        mLoadingDialog = null;
    }

    protected abstract void setRootView(Bundle savedInstanceState);

    protected void init() {

    }

    protected void start() {

    }

    protected void restart() {

    }

    protected void resume() {

    }

    protected void pause() {

    }

    protected void stop() {

    }

    protected void destroy() {

    }

    protected void setListeners() {

    }

    protected void setStatusBarColor() {
        StatusBarUtil.setLightMode(this);
    }

    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    protected void startActivity(Class<?> cls, Map<String, Object> params) {
        Intent intent = new Intent(this, cls);
        if (null != params) {
            for (String key : params.keySet()) {
                setValueToIntent(intent, key, params.get(key));
            }
        }
        startActivity(intent);
    }

    public static void setValueToIntent(Intent intent, String key, Object val) {
        if (val instanceof Boolean)
            intent.putExtra(key, (Boolean) val);
        else if (val instanceof Boolean[])
            intent.putExtra(key, (Boolean[]) val);
        else if (val instanceof String)
            intent.putExtra(key, (String) val);
        else if (val instanceof String[])
            intent.putExtra(key, (String[]) val);
        else if (val instanceof Integer)
            intent.putExtra(key, (Integer) val);
        else if (val instanceof Integer[])
            intent.putExtra(key, (Integer[]) val);
        else if (val instanceof Long)
            intent.putExtra(key, (Long) val);
        else if (val instanceof Long[])
            intent.putExtra(key, (Long[]) val);
        else if (val instanceof Double)
            intent.putExtra(key, (Double) val);
        else if (val instanceof Double[])
            intent.putExtra(key, (Double[]) val);
        else if (val instanceof Float)
            intent.putExtra(key, (Float) val);
        else if (val instanceof Float[])
            intent.putExtra(key, (Float[]) val);
        else if (val instanceof Serializable) {
            intent.putExtra(key, (Serializable) val);
        }
    }
}
