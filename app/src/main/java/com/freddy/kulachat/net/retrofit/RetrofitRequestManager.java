package com.freddy.kulachat.net.retrofit;

import com.alibaba.fastjson.JSON;
import com.freddy.kulachat.net.config.RequestOptions;
import com.freddy.kulachat.net.config.ResponseModel;
import com.freddy.kulachat.net.interf.IRequestInterface;
import com.freddy.kulachat.net.listener.OnNetResponseListener;
import com.freddy.kulachat.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

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
public class RetrofitRequestManager extends AbstractRequestManager implements IRequestInterface<ResponseModel> {

    public RetrofitRequestManager(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }

    @Override
    public void request(RequestOptions options, OnNetResponseListener listener) {
        this.request(options, null, listener);
    }

    @Override
    public <T> void request(RequestOptions options, Class<T> cls, OnNetResponseListener listener) {
        if (listener != null) listener.onStart();
        Observable<ResponseModel> observable = this.request(options);
        observable.subscribe(new CObserver() {

            @Override
            protected void onCNext(ResponseModel responseModel) {
                try {
                    if (listener == null) return;
                    String data = responseModel.getData();
                    if (cls == null || StringUtil.isEmpty(data)) {
                        listener.onSucceed(responseModel);
                    } else {
                        if (List.class.isAssignableFrom(cls)) {
                            listener.onSucceed(parseArray(data, cls));
                        } else {
                            listener.onSucceed(parseObject(data, cls));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onCError(int errCode, String errMsg) {
                super.onCError(errCode, errMsg);
                if (listener != null) listener.onFailed(errCode, errMsg);
            }

            @Override
            protected void onCFinish() {
                super.onCFinish();
                if (listener != null) listener.onFinished();
            }

            @Override
            protected String getFunction() {
                return options.getFunction();
            }

            @Override
            protected String getBaseUrl() {
                return options.getBaseUrl();
            }

            @Override
            protected boolean showErrTips() {
                if (listener != null) return listener.showErrTips();
                return super.showErrTips();
            }
        });
    }

    @Override
    public Observable<ResponseModel> request(RequestOptions options) {
        Observable<String> observable = execRequest(options);
        return observable.flatMap((Function<String, ObservableSource<ResponseModel>>) s -> {
            ResponseModel responseModel = JSON.parseObject(s, ResponseModel.class);
            return Observable.just(responseModel);
        });
    }
}
