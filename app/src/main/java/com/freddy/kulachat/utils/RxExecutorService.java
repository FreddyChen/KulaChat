package com.freddy.kulachat.utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/26 23:54
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class RxExecutorService {

    private RxExecutorService() { }

    public static RxExecutorService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final RxExecutorService INSTANCE = new RxExecutorService();
    }

    public void delay(long delay, TimeUnit unit, Scheduler subscribeScheduler, Scheduler observeScheduler, Observer observer) {
        Observable.timer(delay, unit)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(observer);
    }

    public void interval(long period, TimeUnit unit, Scheduler subscribeScheduler, Scheduler observeScheduler, Observer observer) {
        Observable.interval(period, unit)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(observer);
    }

    public void intervalRange(long start, long count, long initialDelay, long period, TimeUnit unit, Scheduler subscribeScheduler, Scheduler observeScheduler, Observer observer) {
        Observable.intervalRange(start, count, initialDelay, period, unit)
                .subscribeOn(subscribeScheduler)
                .observeOn(observeScheduler)
                .subscribe(observer);
    }
}
