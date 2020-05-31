package com.freddy.kulachat.view.chat;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.freddy.kulachat.R;
import com.freddy.kulachat.config.CConfig;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/31 23:47
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class ChatLoadMoreView extends FrameLayout {

    @BindView(R.id.iv_loading)
    ImageView mLoadingImageView;

    public ChatLoadMoreView(@NonNull Context context) {
        this(context, null);
    }

    public ChatLoadMoreView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatLoadMoreView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.view_chat_load_more, this, true);
        ButterKnife.bind(this, view);
    }

    public void show() {
        setVisibility(VISIBLE);
        getHeaderLoadingAnimator().start();
    }

    public void hide() {
        setVisibility(GONE);
        getHeaderLoadingAnimator().cancel();
    }

    private ObjectAnimator mHeaderLoadingAnimator;

    private ObjectAnimator getHeaderLoadingAnimator() {
        if (mHeaderLoadingAnimator == null) {
            mHeaderLoadingAnimator = ObjectAnimator.ofFloat(mLoadingImageView, CConfig.ANIMATOR_ROTATION, 0.0f, 359.9f);
            mHeaderLoadingAnimator.setDuration(1000);
            mHeaderLoadingAnimator.setInterpolator(new LinearInterpolator());
            mHeaderLoadingAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mHeaderLoadingAnimator.setRepeatMode(ValueAnimator.RESTART);
        }

        return mHeaderLoadingAnimator;
    }
}
