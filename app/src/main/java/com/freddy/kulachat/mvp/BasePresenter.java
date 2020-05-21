package com.freddy.kulachat.mvp;

import java.lang.ref.WeakReference;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/22 01:38
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public abstract class BasePresenter<V extends BaseView> {

    private WeakReference<V> viewReference;
    protected V view;

    public void attachView(V view) {
        viewReference = new WeakReference<>(view);
    }

    public void detachView() {
        if(viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }

    protected boolean isViewAttached() {
        if(viewReference != null) {
            view = viewReference.get();
        }

        return view != null;
    }
}
