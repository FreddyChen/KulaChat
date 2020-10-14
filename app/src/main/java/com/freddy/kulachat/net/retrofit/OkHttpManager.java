package com.freddy.kulachat.net.retrofit;

import android.util.Log;

import com.freddy.kulachat.BuildConfig;
import com.freddy.kulachat.net.config.NetworkConfig;
import com.ihsanbal.logging.Level;
import com.ihsanbal.logging.LoggingInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.internal.platform.Platform;

public class OkHttpManager {

    private OkHttpManager() {}

    public static OkHttpManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final OkHttpManager INSTANCE = new OkHttpManager();
    }

    public OkHttpClient.Builder getOkHttpClientBuilder() {
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

        return builder;
    }
}
