package com.freddy.kulachat.manager;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CountdownManager implements LifecycleObserver {

    private DisposableObserver disposableObserver;

    public static CountdownManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CountdownManager INSTANCE = new CountdownManager();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destroy() {
        dispose();
    }

    public void startCountdown(long time, CountdownCallback callback) {
        this.startCountdown(1, time, callback);
    }

    public void startCountdown(long period, long time, CountdownCallback callback) {
        disposableObserver = Observable.interval(period, TimeUnit.SECONDS)
                .take(time + 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Long>() {

                    @Override
                    public void onNext(Long aLong) {
                        if (callback != null)
                            callback.onNext(time - aLong);
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

    public interface CountdownCallback {
        void onNext(Long time);

        void onComplete();
    }

    public void dispose() {
        if (disposableObserver != null && !disposableObserver.isDisposed()) {
            disposableObserver.dispose();
        }
        disposableObserver = null;
    }
}
