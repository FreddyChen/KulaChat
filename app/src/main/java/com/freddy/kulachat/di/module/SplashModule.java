package com.freddy.kulachat.di.module;

import android.util.Log;

import com.freddy.kulachat.di.scope.ActivityScope;
import com.freddy.kulachat.view.main.SplashActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import dagger.Module;
import dagger.Provides;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/26 03:08
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
@Module
public class SplashModule {

    public SplashModule() {
        Log.d("SplashModule", "SplashModule()");
    }

    @ActivityScope
    @Provides
    public RxPermissions provideRxPermission(SplashActivity activity) {
        return new RxPermissions(activity);
    }
}
