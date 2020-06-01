package com.freddy.kulachat.view.chat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.freddy.kulachat.R;
import com.freddy.kulachat.config.AppConfig;
import com.freddy.kulachat.utils.DensityUtil;
import com.freddy.kulachat.utils.UIUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import butterknife.ButterKnife;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/01 20:56
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class ExpressionPanel extends LinearLayout {

    private Context mContext;
    private int keyboardHeight;

    public ExpressionPanel(@NonNull Context context) {
        this(context, null);
    }

    public ExpressionPanel(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ExpressionPanel(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.view_expression_panel, this, true);
        ButterKnife.bind(this, view);
        init();
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        keyboardHeight = AppConfig.readKeyboardHeight();
        if(keyboardHeight == 0) {
            keyboardHeight = DensityUtil.dp2px(mContext, 247);
            AppConfig.saveKeyboardHeight(keyboardHeight);
        }
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = keyboardHeight;
        setLayoutParams(layoutParams);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    private void init() {
        setBackgroundColor(ContextCompat.getColor(mContext, R.color.c_0072ff));
    }
}
