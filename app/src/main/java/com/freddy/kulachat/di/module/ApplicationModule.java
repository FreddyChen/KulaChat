package com.freddy.kulachat.di.module;

import android.content.Context;
import android.util.Log;

import com.freddy.kulachat.KulaApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 18:31
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
@Module
public class ApplicationModule {

    @Singleton
    @Provides
    Context provideContext(KulaApp app) {
        Log.d("ApplicationModule", "provideContext() app = " + app);
        return app;
    }
}
