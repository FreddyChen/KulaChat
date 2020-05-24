package com.freddy.kulachat.net.retrofit;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.freddy.kulachat.BuildConfig;
import com.freddy.kulachat.net.config.NetworkConfig;
import com.freddy.kulachat.net.config.RequestMethod;
import com.freddy.kulachat.net.config.RequestOptions;
import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.interf.IRequestInterface;
import com.freddy.kulachat.net.retrofit.converter.FastJsonConverterFactory;
import com.freddy.kulachat.utils.StringUtil;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/24 22:44
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class RetrofitRequestManager implements IRequestInterface {

    private static final String TAG = RetrofitRequestManager.class.getSimpleName();
    private RetrofitWrapper retrofitWrapper;
    private Retrofit retrofit;

    private RetrofitRequestManager() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder().connectTimeout(NetworkConfig.REQUEST_TIMEOUT, TimeUnit.MILLISECONDS);
        if (BuildConfig.LOG_DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {
                Log.d("OkHttpClient", "message = " + message);
            }).setLevel(HttpLoggingInterceptor.Level.BASIC);
            clientBuilder.addNetworkInterceptor(loggingInterceptor);
        }

        retrofitWrapper = new RetrofitWrapper()
                .setBaseUrl(BuildConfig.SERVER_URL)
                .addConverterFactory(FastJsonConverterFactory.create())// retrofit对于解析器是由添加的顺序分别试用的，解析成功就直接返回，失败则调用下一个解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .setClient(clientBuilder.build());
        retrofit = retrofitWrapper.build();
    }

    public static RetrofitRequestManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RetrofitRequestManager INSTANCE = new RetrofitRequestManager();
    }

    @Override
    public Observable<ResponseModel> request(RequestOptions options) {
        if (options == null) {
            Log.w(TAG, "request() failure, reason: options is null.");
            return null;
        }

        String baseUrl = options.getBaseUrl();
        if (StringUtil.isNotEmpty(baseUrl)) {
            retrofitWrapper.setBaseUrl(baseUrl);
        }

        String function = options.getFunction();
        if (StringUtil.isEmpty(function)) {
            Log.w(TAG, "request() failure, reason: function is null or null.");
            return null;
        }

        RequestMethod method = options.getMethod();
        if (method == null) {
            method = RequestMethod.GET;
        }

        IApiService apiService = retrofit.create(IApiService.class);
        Map<String, Object> params = options.getParams();
        Observable<ResponseModel> observable = null;
        switch (method) {
            case GET: {
                if (params == null || params.isEmpty()) {
                    observable = apiService.get(function);
                } else {
                    observable = apiService.get(function, params);
                }
                break;
            }

            case POST: {
                if (params == null || params.isEmpty()) {
                    observable = apiService.post(function);
                } else {
                    observable = apiService.post(function, convertParamsToRequestBody(params));
                }
                break;
            }
        }

        return observable;
    }

    private RequestBody convertParamsToRequestBody(Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(params));
        }

        return null;
    }
}
