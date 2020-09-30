package com.freddy.kulachat.net.retrofit;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.freddy.kulachat.BuildConfig;
import com.freddy.kulachat.net.config.RequestMethod;
import com.freddy.kulachat.net.config.RequestOptions;
import com.freddy.kulachat.net.retrofit.converter.FastJsonConverterFactory;
import com.freddy.kulachat.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/27 16:09
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public abstract class AbstractRequestManager {

    private String TAG;
    private RetrofitWrapper mRetrofitWrapper;
    private Map<String, Retrofit> mRetrofitMap = new HashMap<>();

    AbstractRequestManager(OkHttpClient okHttpClient) {
        TAG = getClass().getSimpleName();
        mRetrofitWrapper = new RetrofitWrapper()
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .setClient(okHttpClient);
    }

    protected Observable<String> execRequest(RequestOptions options) {
        if (options == null) {
            Log.w(TAG, "request() failure, reason: options is null.");
            return null;
        }

        String baseUrl = options.getBaseUrl();
        if (StringUtil.isEmpty(baseUrl)) {
            baseUrl = BuildConfig.SERVER_URL;
        }
        mRetrofitWrapper.setBaseUrl(baseUrl);

        Retrofit retrofit;
        if (mRetrofitMap.containsKey(baseUrl)) {
            retrofit = mRetrofitMap.get(baseUrl);
        } else {
            retrofit = mRetrofitWrapper.build();
            mRetrofitMap.put(baseUrl, retrofit);
        }
        if (retrofit == null) {
            Log.w(TAG, "request() failure, reason: retrofit instance is null.");
            return null;
        }
        Log.d(TAG, "retrofit = " + retrofit.hashCode());

        String function = options.getFunction();
        if (StringUtil.isEmpty(function)) {
            Log.w(TAG, "request() failure, reason: function is null or null.");
            return null;
        }

        RequestMethod method = options.getMethod();

        IApiService apiService = retrofit.create(IApiService.class);
        Map<String, Object> params = options.getParams();
        Observable observable = null;
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

        if(observable == null) return null;

        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private RequestBody convertParamsToRequestBody(Map<String, Object> params) {
        if (params != null && !params.isEmpty()) {
            return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), JSON.toJSONString(params));
        }

        return null;
    }
}
