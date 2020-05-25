package com.freddy.kulachat.net.retrofit;

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
    private OkHttpClient.Builder clientBuilder;

    public RetrofitWrapper setBaseUrl(String baseUrl) {
        builder.baseUrl(baseUrl);
        return this;
    }

    public RetrofitWrapper setClientBuilder(OkHttpClient.Builder clientBuilder) {
        this.clientBuilder = clientBuilder;
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

        private Map<String, String> headers;

        HeaderInterceptor(Map<String, String> headers) {
            this.headers = headers;
        }

        @NotNull
        @Override
        public Response intercept(@NotNull Chain chain) throws IOException {
            Request.Builder requestBuilder = chain.request().newBuilder();
            for(Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    }

    public Retrofit build() {
        List<Interceptor> interceptors = clientBuilder.interceptors();
        if(!interceptors.isEmpty()) {
            for(Interceptor interceptor : interceptors) {
                if(interceptor.getClass().equals(HeaderInterceptor.class)) {
                    interceptors.remove(interceptor);
                    break;
                }
            }
        }

        clientBuilder.addInterceptor(new HeaderInterceptor(getHeaders())).build();
        builder.client(clientBuilder.build());
        return builder.build();
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("name", "freddyc");
        headers.put("age", "25");
        return headers;
    }
}
