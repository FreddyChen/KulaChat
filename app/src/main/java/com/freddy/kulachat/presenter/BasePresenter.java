package com.freddy.kulachat.presenter;

import com.freddy.kulachat.view.IBaseView;

import java.lang.ref.WeakReference;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 16:06
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public abstract class BasePresenter<V extends IBaseView> {

    private WeakReference<V> viewReference;
    protected V view;

    public void attachView(V view) {
        viewReference = new WeakReference<>(view);
    }

    public void detachView() {
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }

    protected boolean isActive() {
        if (viewReference != null) {
            view = viewReference.get();
        }

        return view != null;
    }
}
