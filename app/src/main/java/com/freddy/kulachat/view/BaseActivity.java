package com.freddy.kulachat.view;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.CircularPropagation;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.view.Window;

import com.freddy.event.CEventCenter;
import com.freddy.event.I_CEventListener;
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
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView, I_CEventListener {

    @Inject
    public P presenter;

    private Unbinder unbinder;

    protected BaseActivity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        try {
            AndroidInjection.inject(this);
        }catch (IllegalArgumentException e) {
        }
        super.onCreate(savedInstanceState);
        if(hasTransition()) {
            getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.END);
            getWindow().setEnterTransition(slide);

            Explode explode = new Explode();
            explode.setPropagation(new CircularPropagation());
            getWindow().setExitTransition(explode);
        }
        activity = this;
        CActivityManager.getInstance().addActivityToStack(activity);
        setRootView(savedInstanceState);
        setStatusBarColor();
        unbinder = ButterKnife.bind(this);
        init();
        setListeners();

        String[] events = getEvents();
        if(events != null && events.length > 0) {
            CEventCenter.registerEventListener(this, events);
        }
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
        destroy();
        if (presenter != null) {
            presenter.detachView();
        }
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }

        String[] events = getEvents();
        if(events != null && events.length > 0) {
            CEventCenter.unregisterEventListener(this, events);
        }

        super.onDestroy();
        CActivityManager.getInstance().removeActivityFromStack(activity);
    }

    private CLoadingDialog mLoadingDialog;
    @Override
    public void showLoadingDialog() {
        this.showLoadingDialog("加载中", true, true);
    }

    @Override
    public void showLoadingDialog(String title) {
        this.showLoadingDialog(title, true, true);
    }

    @Override
    public void showLoadingDialog(boolean cancelable, boolean canceledOnTouchOutside) {
        this.showLoadingDialog("加载中", true, true);
    }

    @Override
    public void showLoadingDialog(String title, boolean cancelable, boolean canceledOnTouchOutside) {
        mLoadingDialog = new CLoadingDialog.Builder()
                .setTitle(title)
                .setCancelable(cancelable)
                .setCanceledOnTouchOutside(canceledOnTouchOutside)
                .build();
        mLoadingDialog.show(getSupportFragmentManager(), getClass().getSimpleName());
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

    protected String[] getEvents() {
        return null;
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

    @Override
    public void onCEvent(String topic, int msgCode, int resultCode, Object obj) {

    }

    @Override
    public void startActivity(Intent intent) {
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    protected boolean hasTransition() {
        return true;
    }
}
