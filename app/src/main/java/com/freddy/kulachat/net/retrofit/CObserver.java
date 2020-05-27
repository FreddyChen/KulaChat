package com.freddy.kulachat.net.retrofit;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.freddy.kulachat.net.config.ResponseModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/25 01:36
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public abstract class CObserver implements Observer<ResponseModel> {

    private static final String TAG = CObserver.class.getSimpleName();
    private Disposable mDisposable;

    @Override
    public void onSubscribe(Disposable d) {
        this.mDisposable = d;
    }

    @Override
    public void onNext(ResponseModel responseModel) {
        int code = responseModel.getCode();
        if (code == 0) {
            onCNext(responseModel);
        }
    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG, "onError() e = " + e);
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete()");
    }

    protected abstract void onCNext(ResponseModel responseModel);

    protected <T> T parseObject(String data, Class<T> cls) {
        return JSON.parseObject(data, cls);
    }

    protected <T> List<T> parseArray(String data, Class<T> cls) {
        return JSON.parseArray(data, cls);
    }
}
