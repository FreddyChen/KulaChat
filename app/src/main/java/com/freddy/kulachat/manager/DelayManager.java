package com.freddy.kulachat.manager;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DelayManager implements LifecycleObserver {

    private DisposableObserver disposableObserver;

    public static DelayManager getInstance() {
        return DelayManager.SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final DelayManager INSTANCE = new DelayManager();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy() {
        dispose();
    }

    public void startDelay(long time, TimeUnit unit, DelayCallback callback) {
        disposableObserver = Observable.timer(time, unit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {

                    @Override
                    public void onNext(Long aLong) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        if (callback != null) callback.onComplete();
                    }
                });
    }

    public interface DelayCallback {
        void onComplete();
    }

    public void dispose() {
        if (disposableObserver != null && !disposableObserver.isDisposed()) {
            disposableObserver.dispose();
        }
        disposableObserver = null;
    }
}
