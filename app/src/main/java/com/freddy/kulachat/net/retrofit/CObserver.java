package com.freddy.kulachat.net.retrofit;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.freddy.kulachat.net.config.ResponseModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.http.POST;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/25 01:36
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public abstract class CObserver implements Observer<ResponseModel> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(ResponseModel responseModel) {
        int code = responseModel.getCode();
        if(code == 0) {
            onCNext(responseModel);
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    protected abstract void onCNext(ResponseModel responseModel);

    protected <T> T parseObject(String data, Class<T> cls) {
        return JSON.parseObject(data, cls);
    }

    protected <T> List<T> parseArray(String data, Class<T> cls) {
        return JSON.parseArray(data, cls);
    }
}
