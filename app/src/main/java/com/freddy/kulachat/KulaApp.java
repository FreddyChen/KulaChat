package com.freddy.kulachat;

import android.app.Activity;
import android.app.Application;

import com.freddy.kulachat.di.component.AppComponent;
import com.freddy.kulachat.di.component.DaggerAppComponent;
import com.freddy.kulachat.utils.CrashHandler;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/21 17:27
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class KulaApp extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    private static KulaApp instance;

    private AppComponent mAppComponent;

    public static KulaApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mAppComponent = DaggerAppComponent.builder().build();
        mAppComponent.inject(this);
        CrashHandler.getInstance().init();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
