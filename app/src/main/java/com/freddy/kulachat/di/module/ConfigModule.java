package com.freddy.kulachat.di.module;

import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 18:38
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
@Module
public abstract class ConfigModule {

    @Singleton
    @Provides
    static Gson provideGson() {
        return new Gson();
    }

//    @Singleton
//    @Provides
//    static MainModel provideMainModel() {
//        return new MainModel();
//    }
}
