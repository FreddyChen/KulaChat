package com.freddy.kulachat.view;

import android.content.Intent;
import android.os.Bundle;

import com.freddy.kulachat.R;
import com.freddy.kulachat.presenter.BasePresenter;
import com.jaeger.library.StatusBarUtil;

import java.io.Serializable;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            AndroidInjection.inject(this);
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        CActivityManager.getInstance().addActivityToStack(this);
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
        CActivityManager.getInstance().removeActivityFromStack(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showLoading(String content) {

    }

    @Override
    public void hideLoading() {

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
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, R.color.colorPrimaryDark), 75);
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
