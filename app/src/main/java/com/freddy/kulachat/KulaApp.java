package com.freddy.kulachat;

import com.freddy.kulachat.di.component.DaggerApplicationComponent;
import com.freddy.kulachat.utils.CrashHandler;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/21 17:27
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class KulaApp extends DaggerApplication {

    private static KulaApp instance;

    public static KulaApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        CrashHandler.getInstance().init();
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerApplicationComponent.builder().application(this).build();
    }
}
