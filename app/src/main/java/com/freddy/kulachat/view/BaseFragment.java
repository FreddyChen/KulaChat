package com.freddy.kulachat.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.freddy.event.CEventCenter;
import com.freddy.event.I_CEventListener;
import com.freddy.kulachat.presenter.BasePresenter;
import com.freddy.kulachat.widget.CLoadingDialog;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/30 05:09
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements IBaseView, I_CEventListener {

    @Inject
    protected P presenter;
    private Unbinder unbinder;
    protected BaseFragment fragment;
    private int layoutResId;
    private View layoutView;

    @Override
    public void onAttach(@NonNull Context context) {
        try {
            AndroidSupportInjection.inject(this);
        }catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        super.onAttach(context);
        attach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = this;
        setRootView(savedInstanceState);
        if(layoutView == null) {
            if(layoutResId != 0) {
                layoutView = inflater.inflate(layoutResId, container, false);
            }
        }
        unbinder = ButterKnife.bind(this, layoutView);

        init();
        setListeners();

        String[] events = getEvents();
        if(events != null && events.length > 0) {
            CEventCenter.registerEventListener(this, events);
        }

        return layoutView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activiyCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (presenter != null) {
            presenter.attachView(this);
        }

        start();
    }

    @Override
    public void onResume() {
        super.onResume();
        resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        pause();
    }

    @Override
    public void onStop() {
        super.onStop();
        stop();
    }

    @Override
    public void onDestroyView() {
        String[] events = getEvents();
        if(events != null && events.length > 0) {
            CEventCenter.unregisterEventListener(this, events);
        }
        super.onDestroyView();
        destroyView();
    }

    @Override
    public void onDestroy() {
        if (presenter != null) {
            presenter.detachView();
        }

        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        destroy();
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        detach();
    }

    protected void attach(Context context) {

    }

    protected void activiyCreated(Bundle savedInstanceState) {

    }

    protected void start() {

    }

    protected void resume() {

    }

    protected void pause() {

    }

    protected void stop() {

    }

    protected void destroyView() {

    }

    protected void destroy() {

    }

    protected void detach() {

    }

    protected abstract void setRootView(Bundle saveInstanceState);

    protected void setLayout(int layoutResId) {
        this.layoutResId = layoutResId;
    }

    protected void setLayout(View layoutView) {
        this.layoutView = layoutView;
    }

    protected void init() {

    }

    protected void setListeners() {

    }

    protected String[] getEvents() {
        return null;
    }

    @Override
    public void showLoading() {
        this.showLoading("加载中");
    }

    private CLoadingDialog mLoadingDialog;
    @Override
    public void showLoading(String title) {
        mLoadingDialog = new CLoadingDialog.Builder()
                .setTitle(title)
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .build();
        mLoadingDialog.show(getChildFragmentManager(), null);
    }

    @Override
    public void hideLoading() {
        if(mLoadingDialog != null && mLoadingDialog.isVisible()) {
            mLoadingDialog.dismiss();
        }
        mLoadingDialog = null;
    }

    @Override
    public void onCEvent(String topic, int msgCode, int resultCode, Object obj) {

    }
}
