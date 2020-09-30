package com.freddy.kulachat.net.retrofit;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.freddy.kulachat.KulaApp;
import com.freddy.kulachat.net.config.ResponseCode;
import com.freddy.kulachat.net.config.ResponseModel;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

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
        Log.d(TAG, "onNext()\tresponseModel = " + responseModel);
        int code = responseModel.getCode();
        if (code == ResponseCode.SUCCEED.getCode()) {
            onCNext(responseModel);
        } else {
            onCError(responseModel.getCode(), responseModel.getMsg());
        }

        onComplete();
    }

    @Override
    public void onError(Throwable e) {
        int errorCode = ResponseCode.UNKNOWN.getCode();
        String errorMsg = ResponseCode.UNKNOWN.getMessage();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            retrofit2.Response response = httpException.response();
            if (response != null) {
                try {
                    errorMsg = response.errorBody().string();
                    errorCode = response.code();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        } else if (e instanceof SocketTimeoutException) {
            SocketTimeoutException socketTimeoutException = (SocketTimeoutException) e;
            Log.e(TAG, "errorMsg = " + socketTimeoutException.getMessage());
            errorCode = ResponseCode.TIMEOUT.getCode();
            errorMsg = ResponseCode.TIMEOUT.getMessage();
        } else {
            Log.e(TAG, "errorMsg = " + e.getMessage());
        }

        Log.d(TAG, "onError()\tfunction : " + getBaseUrl() + getFunction() + " request error, reason : " + errorMsg);
        onCError(errorCode, errorMsg);
        onComplete();
    }

    @Override
    public void onComplete() {
        onCFinish();
        if (mDisposable != null) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }

    protected abstract void onCNext(ResponseModel responseModel);

    protected void onCError(int errCode, String errMsg) {
        if (showErrTips()) Toasty.error(KulaApp.getInstance(), errMsg).show();
    }

    protected void onCFinish() {

    }

    protected <T> T parseObject(String data, Class<T> cls) {
        return JSON.parseObject(data, cls);
    }

    protected <T> List<T> parseArray(String data, Class<T> cls) {
        return JSON.parseArray(data, cls);
    }

    protected abstract String getFunction();

    protected abstract String getBaseUrl();

    protected boolean showErrTips() {
        return true;
    }
}
