package com.freddy.kulachat.net.retrofit;

import com.alibaba.fastjson.JSON;
import com.freddy.kulachat.net.config.DingDingResponseModel;
import com.freddy.kulachat.net.config.RequestOptions;
import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.interf.IRequestInterface;
import com.freddy.kulachat.net.listener.OnNetResponseListener;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import okhttp3.OkHttpClient;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/27 14:33
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class DingDingRetrofitRequestManager extends AbstractRequestManager implements IRequestInterface<DingDingResponseModel> {

    public DingDingRetrofitRequestManager(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }

    @Override
    public void request(RequestOptions options, OnNetResponseListener listener) {

    }

    @Override
    public <T> void request(RequestOptions options, Class<T> cls, OnNetResponseListener listener) {

    }

    @Override
    public Observable<DingDingResponseModel> request(RequestOptions options) {
        Observable<String> observable = execRequest(options);
        return observable.flatMap((Function<String, ObservableSource<DingDingResponseModel>>) s -> {
            DingDingResponseModel responseModel = JSON.parseObject(s, DingDingResponseModel.class);
            return Observable.just(responseModel);
        });
    }
}
