package com.freddy.kulachat.listener;

import android.util.Log;

import com.freddy.event.CEvent;
import com.freddy.event.CEventCenter;
import com.freddy.kulachat.event.Events;
import com.freddy.kulachat.event.obj.IMSConnectStatusEventObj;
import com.freddy.kulaims.config.IMSConnectStatus;
import com.freddy.kulaims.listener.IMSConnectStatusListener;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class OnIMSConnectStatusListener implements IMSConnectStatusListener {

    @Override
    public void onUnconnected() {
        dispatchEvent(IMSConnectStatus.Unconnected);
    }

    @Override
    public void onConnecting() {
        dispatchEvent(IMSConnectStatus.Connecting);
    }

    @Override
    public void onConnected() {
        dispatchEvent(IMSConnectStatus.Connected);
    }

    @Override
    public void onConnectFailed(int errCode, String errMsg) {
        dispatchEvent(IMSConnectStatus.ConnectFailed);
    }


    private void dispatchEvent(IMSConnectStatus status) {
        Log.d("Freddy", "thread1 = " + Thread.currentThread().getId());
        Observable.just(status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IMSConnectStatus>() {
                    private Disposable mDisposable = null;
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisposable = d;
                    }

                    @Override
                    public void onNext(IMSConnectStatus imsConnectStatus) {
                        Log.d("Freddy", "thread2 = " + Thread.currentThread().getId());
                        IMSConnectStatusEventObj eventObj = new IMSConnectStatusEventObj();
                        eventObj.setImsConnectStatus(status);
                        CEvent event = new CEvent();
                        event.setTopic(Events.EVENT_IMS_CONNECT_STATUS);
                        event.setObj(eventObj);
                        CEventCenter.dispatchEvent(event);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(mDisposable != null && !mDisposable.isDisposed()) {
                            mDisposable.dispose();
                        }
                        mDisposable = null;
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
