package com.freddy.kulachat;

import android.app.Application;

//import com.freddy.kulachat.di.component.ApplicationComponent;
//import com.freddy.kulachat.di.component.DaggerApplicationComponent;
import com.freddy.kulachat.di.component.ApplicationComponent;
import com.freddy.kulachat.di.component.DaggerApplicationComponent;
import com.freddy.kulachat.utils.CrashHandler;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/21 17:27
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class KulaApp extends Application implements HasAndroidInjector {

    private static KulaApp instance;

    public static KulaApp getInstance() {
        return instance;
    }

    public ApplicationComponent applicationComponent;

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().build();
        applicationComponent.inject(this);
        instance = this;
        CrashHandler.getInstance().init();
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }
}
