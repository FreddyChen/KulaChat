package com.freddy.kulachat.view.main;

import android.os.Bundle;
import android.util.Log;

import com.freddy.kulachat.R;
import com.freddy.kulachat.presenter.NullablePresenter;
import com.freddy.kulachat.view.BaseActivity;
import com.jaeger.library.StatusBarUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/24 01:52
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class SplashActivity extends BaseActivity<NullablePresenter> {

    @Inject
    RxPermissions rxPermissions;
    private Disposable mCheckPermissionDisposable;

    @Override
    protected void setRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        Log.d(SplashActivity.class.getSimpleName(), "rxPermissions = " + rxPermissions);
    }

    @Override
    protected void setStatusBarColor() {
        StatusBarUtil.setTransparent(this);
    }

    private void checkNecessaryPermissions() {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(new Observer<Long>() {

                    @Override
                    public void onSubscribe(Disposable d) {
                        mCheckPermissionDisposable = d;
                    }

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void destroy() {
        if(mCheckPermissionDisposable != null && !mCheckPermissionDisposable.isDisposed()) {
            mCheckPermissionDisposable.dispose();
        }
        mCheckPermissionDisposable = null;
    }
}
