package com.freddy.kulachat.net.listener;

public abstract class OnNetResponseListener<T> implements IResponseListener<T> {

    @Override
    public void onStart() {

    }

    @Override
    public void onSucceed(T t) {

    }

    @Override
    public void onFailed(int errCode, String errMsg) {

    }

    @Override
    public void onFinished() {

    }

    public boolean showErrTips() { return true; }
}
