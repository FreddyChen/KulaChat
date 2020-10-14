package com.freddy.kulachat.net.retrofit;

import android.util.Log;

import com.freddy.kulachat.model.user.UserManager;
import com.freddy.kulachat.net.config.NetworkConfig;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/24 22:09
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class RetrofitWrapper {

    private Retrofit.Builder builder = new Retrofit.Builder();
    private OkHttpClient okHttpClient;

    public RetrofitWrapper setBaseUrl(String baseUrl) {
        builder.baseUrl(baseUrl);
        return this;
    }

    public RetrofitWrapper setClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
        return this;
    }

    public <T extends Converter.Factory> RetrofitWrapper addConverterFactory(T factory) {
        builder.addConverterFactory(factory);
        return this;
    }

    public <T extends CallAdapter.Factory> RetrofitWrapper addCallAdapterFactory(T factory) {
        builder.addCallAdapterFactory(factory);
        return this;
    }

    private static class HeaderInterceptor implements Interceptor {

        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            Map<String, String> headers = getHeaders();
            Request.Builder requestBuilder = chain.request().newBuilder();
            for(Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    }

    public Retrofit build() {
        OkHttpClient.Builder okHttpClientBuilder = okHttpClient.newBuilder();
        List<Interceptor> interceptors = okHttpClientBuilder.interceptors();
        if(!interceptors.isEmpty()) {
            for(Interceptor interceptor : interceptors) {
                if(interceptor.getClass().equals(HeaderInterceptor.class)) {
                    interceptors.remove(interceptor);
                    break;
                }
            }
        }

        okHttpClientBuilder.addInterceptor(new HeaderInterceptor()).build();
        builder.client(okHttpClientBuilder.build());
        return builder.build();
    }

    public static Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        String token = UserManager.getInstance().getToken();
        if(token != null) {
            headers.put(NetworkConfig.PARAM_USER_TOKEN, token);
        }
        return headers;
    }
}
