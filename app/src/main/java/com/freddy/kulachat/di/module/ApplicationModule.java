package com.freddy.kulachat.di.module;

import android.util.Log;

import com.freddy.kulachat.BuildConfig;
import com.freddy.kulachat.net.config.NetworkConfig;
import com.freddy.kulachat.net.retrofit.DingDingRetrofitRequestManager;
import com.freddy.kulachat.net.retrofit.OkHttpManager;
import com.freddy.kulachat.net.retrofit.RetrofitRequestManager;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/30 22:22
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
@Module
public class ApplicationModule {

    @Singleton
    @Provides
    static RetrofitRequestManager provideRetrofitRequestManager(OkHttpClient okHttpClient) {
        return new RetrofitRequestManager(okHttpClient);
    }

    @Singleton
    @Provides
    static DingDingRetrofitRequestManager provideDingDingRetrofitRequestManager(OkHttpClient okHttpClient) {
        return new DingDingRetrofitRequestManager(okHttpClient);
    }

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient() {
        return OkHttpManager.getInstance().getOkHttpClientBuilder().build();
    }
}
