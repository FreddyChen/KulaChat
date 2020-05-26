package com.freddy.kulachat.view.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Bundle;

import com.freddy.kulachat.R;
import com.freddy.kulachat.presenter.NullablePresenter;
import com.freddy.kulachat.utils.RxExecutorService;
import com.freddy.kulachat.view.BaseActivity;
import com.freddy.kulachat.view.home.HomeActivity;
import com.jaeger.library.StatusBarUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
    private Disposable mExitAppDisposable;
    private static final String[] NECESSARY_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void setRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        checkNecessaryPermissions();
    }

    @Override
    protected void setStatusBarColor() {
        StatusBarUtil.setTransparent(this);
    }

    private void checkNecessaryPermissions() {
        RxExecutorService.getInstance().delay(2, TimeUnit.SECONDS, Schedulers.io(), AndroidSchedulers.mainThread(), new Observer<Long>() {

            @Override
            public void onSubscribe(Disposable d) {
                mCheckPermissionDisposable = d;
            }

            @Override
            public void onNext(Long aLong) {

            }

            @Override
            public void onError(Throwable e) {
                dispose(mCheckPermissionDisposable);
            }

            @SuppressLint("CheckResult")
            @Override
            public void onComplete() {
                rxPermissions.request(NECESSARY_PERMISSIONS)
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(granted -> {
                            if(granted) {
                                initPage();
                            }else {
                                RxExecutorService.getInstance().delay(3, TimeUnit.SECONDS, Schedulers.io(), AndroidSchedulers.mainThread(), mExitAppObserver);
                            }
                        });
            }
        });
    }

    private Observer<Long> mExitAppObserver = new Observer<Long>() {

        @Override
        public void onSubscribe(Disposable d) {
            Toasty.error(activity, "缺少必须权限，程序即将关闭", Toasty.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(Long aLong) {

        }

        @Override
        public void onError(Throwable e) {
            dispose(mExitAppDisposable);
        }

        @Override
        public void onComplete() {
            System.exit(0);
        }
    };

    private void initPage() {
        startActivity(HomeActivity.class);
        finish();
    }

    @Override
    protected void destroy() {
        dispose(mCheckPermissionDisposable);
        dispose(mExitAppDisposable);
    }

    private void dispose(Disposable d) {
        if (d != null && !d.isDisposed()) {
            d.dispose();
        }
        d = null;
    }
}
