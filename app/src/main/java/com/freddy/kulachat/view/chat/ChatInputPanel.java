package com.freddy.kulachat.view.chat;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

import com.freddy.kulachat.R;
import com.freddy.kulachat.config.AppConfig;
import com.freddy.kulachat.utils.DensityUtil;
import com.freddy.kulachat.utils.UIUtil;
import com.freddy.kulachat.widget.CImageButton;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import butterknife.Unbinder;
import es.dmoral.toasty.Toasty;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/02 20:15
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class ChatInputPanel extends LinearLayout {

    private Unbinder unbinder;
    private Context mContext;
    @BindView(R.id.btn_voice)
    CImageButton mVoiceBtn;
    @BindView(R.id.btn_keyboard)
    CImageButton mKeyboardBtn;
    @BindView(R.id.btn_expression)
    CImageButton mExpressionBtn;
    @BindView(R.id.btn_more)
    CImageButton mMoreBtn;
    @BindView(R.id.et_content)
    ChatEditText mContentEditText;
    @BindView(R.id.expression_panel)
    ExpressionPanel mExpressionPanel;
    private boolean isShowingInputMethod = false;
    private boolean isShowingExpressionPanel = false;

    public ChatInputPanel(Context context) {
        this(context, null);
    }

    public ChatInputPanel(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatInputPanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.view_chat_input_panel, this, true);
        unbinder = ButterKnife.bind(this, view);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
    }

    @OnClick({R.id.btn_voice, R.id.btn_keyboard, R.id.btn_expression, R.id.btn_more})
    void onClickListeners(View v) {
        switch (v.getId()) {
            case R.id.btn_voice: {
                break;
            }
            case R.id.btn_keyboard: {
                break;
            }
            case R.id.btn_expression: {
                isShowingExpressionPanel = mExpressionPanel.getVisibility() == VISIBLE;
                if (isShowingExpressionPanel) {
                    mExpressionBtn.setNormalImageResId(R.drawable.ic_chat_expression_normal);
                    mExpressionBtn.setPressedImageResId(R.drawable.ic_chat_expression_pressed);
                    mExpressionPanel.setVisibility(GONE);
                } else {
                    mExpressionBtn.setNormalImageResId(R.drawable.ic_chat_keyboard_normal);
                    mExpressionBtn.setPressedImageResId(R.drawable.ic_chat_keyboard_pressed);
                    //                    mExpressionPanel.setVisibility(VISIBLE);
                    if (isShowingInputMethod) {
                        if (mOnLayoutAnimatorHandleListener != null) {
                            mOnLayoutAnimatorHandleListener.handleAnimator(-AppConfig.readKeyboardHeight(), -(AppConfig.readKeyboardHeight() + DensityUtil.dp2px(mContext, 36)));
                        }
                    } else {
                        if (mOnLayoutAnimatorHandleListener != null) {
                            mOnLayoutAnimatorHandleListener.handleAnimator(0.0f, -(AppConfig.readKeyboardHeight() + DensityUtil.dp2px(mContext, 36)));
                        }
                    }
                }
                isShowingExpressionPanel = !isShowingExpressionPanel;
                break;
            }
            case R.id.btn_more: {
                break;
            }
        }
    }

    @OnFocusChange(R.id.et_content)
    void onContentEditTextFocusChanged(boolean focused) {
        isShowingInputMethod = focused;
        Log.d("ChatInputPanel", "isShowingInputMethod = " + isShowingInputMethod);
        //        if (focused) {
        //            isShowingInputMethod = true;
        //            Toasty.normal(mContext, "获取焦点", Toasty.LENGTH_SHORT).show();
        //            if (isShowingExpressionPanel) {
        //                if (mOnLayoutAnimatorHandleListener != null) {
        //                    mOnLayoutAnimatorHandleListener.handleAnimator(-(AppConfig.readKeyboardHeight() + DensityUtil.dp2px(mContext, 36)), -AppConfig.readKeyboardHeight());
        //                }
        //            } else {
        //                if (mOnLayoutAnimatorHandleListener != null) {
        //                    mOnLayoutAnimatorHandleListener.handleAnimator(0.0f, -AppConfig.readKeyboardHeight());
        //                }
        //            }
        //            mExpressionBtn.setNormalImageResId(R.drawable.ic_chat_expression_normal);
        //            mExpressionBtn.setPressedImageResId(R.drawable.ic_chat_expression_pressed);
        //            isShowingExpressionPanel = false;
        //        } else {
        //            isShowingInputMethod = false;
        //            Toasty.normal(mContext, "失去焦点", Toasty.LENGTH_SHORT).show();
        //            if (isShowingExpressionPanel) {
        //                if (mOnLayoutAnimatorHandleListener != null) {
        //                    mOnLayoutAnimatorHandleListener.handleAnimator(-(AppConfig.readKeyboardHeight() + DensityUtil.dp2px(mContext, 36)), -AppConfig.readKeyboardHeight());
        //                }
        //            } else {
        //                if (mOnLayoutAnimatorHandleListener != null) {
        //                    mOnLayoutAnimatorHandleListener.handleAnimator(-AppConfig.readKeyboardHeight(), 0.0f);
        //                }
        //            }
        //        }
    }

    @OnEditorAction(R.id.et_content)
    boolean onContentEditTextEditorAction(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            Toasty.success(mContext, "发送", Toasty.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private OnLayoutAnimatorHandleListener mOnLayoutAnimatorHandleListener;

    public void setOnLayoutAnimatorHandleListener(OnLayoutAnimatorHandleListener listener) {
        this.mOnLayoutAnimatorHandleListener = listener;
    }

    public interface OnLayoutAnimatorHandleListener {
        void handleAnimator(float fromValue, float toValue);
    }

    public void reset() {
        UIUtil.loseFocus(mContentEditText);
        UIUtil.hideSoftInput(mContext, mContentEditText);
        mExpressionBtn.setNormalImageResId(R.drawable.ic_chat_expression_normal);
        mExpressionBtn.setPressedImageResId(R.drawable.ic_chat_expression_pressed);
        mExpressionPanel.setVisibility(GONE);
        isShowingExpressionPanel = false;
    }

    public void onSoftKeyboardOpened() {
        Log.d("ChatInputPanel", "onSoftKeyboardOpened()");
    }

    public void onSoftKeyboardClosed() {
        Log.d("ChatInputPanel", "onSoftKeyboardClosed()");
    }
}
