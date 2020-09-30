package com.freddy.kulachat.net.listener;

public interface IResponseListener<T> {

    void onStart();

    void onSucceed(T t);

    void onFailed(int errCode, String errMsg);

    void onFinished();
}
