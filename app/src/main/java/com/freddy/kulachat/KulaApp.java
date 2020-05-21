package com.freddy.kulachat;

import android.app.Application;

import com.freddy.kulachat.utils.CrashHandler;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/21 17:27
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class KulaApp extends Application {

    private static KulaApp instance;

    public static KulaApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        CrashHandler.getInstance().init();
    }
}
