package com.freddy.kulachat.view.main;

import android.Manifest;
import android.os.Bundle;

import com.freddy.kulachat.R;
import com.freddy.kulachat.manager.DelayManager;
import com.freddy.kulachat.model.user.UserManager;
import com.freddy.kulachat.presenter.NullablePresenter;
import com.freddy.kulachat.view.BaseActivity;
import com.freddy.kulachat.view.CActivityManager;
import com.freddy.kulachat.view.home.HomeActivity;
import com.freddy.kulachat.view.user.LoginActivity;
import com.jaeger.library.StatusBarUtil;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import es.dmoral.toasty.Toasty;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

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
    private static final String[] NECESSARY_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    @Override
    protected void setRootView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        checkNecessaryPermissions();
        getLifecycle().addObserver(DelayManager.getInstance());
    }

    @Override
    protected void setStatusBarColor() {
        StatusBarUtil.setTransparent(this);
    }

    private void checkNecessaryPermissions() {
        DelayManager.getInstance().startDelay(2, TimeUnit.SECONDS, () -> rxPermissions.request(NECESSARY_PERMISSIONS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(granted -> {
                    if (granted) {
                        initPage();
                    } else {
                        exitApp();
                    }
                }));
    }

    private void exitApp() {
        Toasty.error(activity, "缺少必须权限，程序即将关闭", Toasty.LENGTH_SHORT).show();
        DelayManager.getInstance().startDelay(3, TimeUnit.SECONDS, () -> {
            System.exit(0);
        });
    }

    private void initPage() {
        if (UserManager.getInstance().checkUserLoginStateFromLocal() && UserManager.getInstance().isCompletedInfo()) {
            startActivity(HomeActivity.class);
        } else {
            startActivity(LoginActivity.class);
        }

        DelayManager.getInstance().startDelay(500, TimeUnit.MILLISECONDS, () -> {
            CActivityManager.getInstance().finishActivity(this);
        });
    }

    @Override
    protected boolean hasTransition() {
        return false;
    }
}
