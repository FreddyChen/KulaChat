package com.freddy.kulachat.di.module;

import android.util.Log;

import com.freddy.kulachat.BuildConfig;
import com.freddy.kulachat.net.config.NetworkConfig;
import com.freddy.kulachat.net.retrofit.DingDingRetrofitRequestManager;
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
 * @date 2020/05/26 01:54
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
@Module
public abstract class AppModule {

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
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(NetworkConfig.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(NetworkConfig.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(NetworkConfig.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        if(BuildConfig.LOG_DEBUG) {
            LoggingInterceptor loggingInterceptor = new LoggingInterceptor.Builder()
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request("Request")
                    .response("Response")
                    .logger((level, tag, message) -> Log.d("OkHttpClient", "level = " + level + ", tag = " + tag + ", message = " + message))
                    .build();
            builder.addNetworkInterceptor(loggingInterceptor);
        }

        return builder.build();
    }
}
