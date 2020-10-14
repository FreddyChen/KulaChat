package com.freddy.kulachat.ims.listener;

import com.freddy.event.CEvent;
import com.freddy.event.CEventCenter;
import com.freddy.kulachat.event.Events;
import com.freddy.kulachat.event.obj.IMSConnectStatusEventObj;
import com.freddy.kulaims.config.IMSConnectStatus;
import com.freddy.kulaims.listener.IMSConnectStatusListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
