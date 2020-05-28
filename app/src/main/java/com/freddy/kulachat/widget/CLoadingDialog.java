package com.freddy.kulachat.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.freddy.kulachat.KulaApp;
import com.freddy.kulachat.R;
import com.freddy.kulachat.config.CConfig;
import com.freddy.kulachat.utils.DensityUtil;
import com.freddy.kulachat.utils.StringUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/29 01:36
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class CLoadingDialog extends DialogFragment {

    @BindView(R.id.iv_loading)
    ImageView mLoadingImageView;
    @BindView(R.id.tv_title)
    TextView mTitleTextView;

    private Unbinder unbinder;
    private Builder mBuilder;
    public CLoadingDialog(Builder builder) {
        this.mBuilder = builder;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_loading, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Dialog dialog = getDialog();
        if(dialog != null) {
            Window window = dialog.getWindow();
            if(window != null) {
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setCancelable(mBuilder == null || mBuilder.cancelable);
                dialog.setCanceledOnTouchOutside(mBuilder != null && mBuilder.canceledOnTouchOutside);
            }
        }

        init();
    }

    private void init() {
        if(mBuilder != null) {
            mTitleTextView.setVisibility(StringUtil.isNotEmpty(mBuilder.title) ? View.VISIBLE : View.GONE);
            mTitleTextView.setText(mBuilder.title);
            mTitleTextView.setTextColor(mBuilder.textColor);
            mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mBuilder.textSize);
        }
        startLoadingAnimator();
    }

    private ObjectAnimator mLoadingAnimator;
    private void startLoadingAnimator() {
        mLoadingAnimator = ObjectAnimator.ofFloat(mLoadingImageView, CConfig.ANIMATOR_ROTATION, 0.0f, 359.9f);
        mLoadingAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mLoadingAnimator.setRepeatMode(ValueAnimator.RESTART);
        mLoadingAnimator.setDuration(1500);
        mLoadingAnimator.setInterpolator(new LinearInterpolator());
        mLoadingAnimator.start();
    }

    private void stopLoadingAnimator() {
        if(mLoadingAnimator != null && mLoadingAnimator.isRunning()) {
            mLoadingAnimator.cancel();
        }
        mLoadingAnimator = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        stopLoadingAnimator();
    }

    public static class Builder {

        private String title;
        private int textColor;
        private int textSize;
        private boolean cancelable;
        private boolean canceledOnTouchOutside;

        public Builder() {
            cancelable = true;
            canceledOnTouchOutside = false;
            textColor = ContextCompat.getColor(KulaApp.getInstance(), R.color.c_ffffff);
            textSize = DensityUtil.sp2px(KulaApp.getInstance(), 14);
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTextColor(int textColor) {
            this.textColor = textColor;
            return this;
        }

        public Builder setTextSize(int textSize) {
            this.textSize = textSize;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.canceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        public CLoadingDialog build() {
            return new CLoadingDialog(this);
        }
    }
}
