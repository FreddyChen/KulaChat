package com.freddy.kulachat.net.retrofit;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
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
    private OkHttpClient client;

    public RetrofitWrapper setBaseUrl(String baseUrl) {
        builder.baseUrl(baseUrl);
        return this;
    }

    public RetrofitWrapper setClient(OkHttpClient client) {
        builder.client(client);
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

    public Retrofit build() {
        return builder.build();
    }
}
