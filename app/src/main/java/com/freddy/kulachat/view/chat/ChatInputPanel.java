package com.freddy.kulachat.view.chat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.freddy.kulachat.R;
import com.freddy.kulachat.utils.UIUtil;
import com.freddy.kulachat.view.BaseActivity;
import com.freddy.kulachat.widget.CImageButton;
import com.freddy.kulachat.widget.SoftKeyboardStateHelper;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import es.dmoral.toasty.Toasty;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/31 20:02
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class ChatInputPanel extends LinearLayout {

    private BaseActivity mActivity;
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
    EditText mContentEditText;
    @BindView(R.id.expression_panel)
    ExpressionPanel mExpressionPanel;

    public ChatInputPanel(Context context) {
        this(context, null);
    }

    public ChatInputPanel(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChatInputPanel(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        mActivity = (BaseActivity) context;
        View view = LayoutInflater.from(context).inflate(R.layout.view_chat_input_panel, this, true);
        ButterKnife.bind(this, view);
        init();
    }

    private void init() {
        setOrientation(VERTICAL);
        mContentEditText.setHorizontallyScrolling(false);
        mContentEditText.setMaxLines(5);
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
                showHideExpressionPanel(mExpressionPanel.getVisibility() != VISIBLE);
                break;
            }
            case R.id.btn_more: {
                break;
            }
        }
    }

    @OnEditorAction(R.id.et_content)
    boolean onContentEditTextEditorAction(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEND) {
            Toasty.success(mContext, "发送", Toasty.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @OnFocusChange(R.id.et_content)
    void onContentEditTextFocusChanged(boolean focused) {
        if(focused) {
            mExpressionBtn.setNormalImageResId(R.drawable.ic_chat_expression_normal);
            mExpressionBtn.setPressedImageResId(R.drawable.ic_chat_expression_pressed);
            mExpressionPanel.postDelayed(mExpressionPanelDelayRunnable, 200);
        }
    }

    private void showHideExpressionPanel(boolean isShow) {
        if (isShow) {
            UIUtil.loseFocus(mContentEditText);
            UIUtil.hideSoftInput(mContext, mContentEditText);
            mExpressionBtn.setNormalImageResId(R.drawable.ic_chat_keyboard_normal);
            mExpressionBtn.setPressedImageResId(R.drawable.ic_chat_keyboard_pressed);
            mExpressionPanel.setVisibility(VISIBLE);
            mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
            if(mOnExpressionPanelOpenListener != null) {
                mOnExpressionPanelOpenListener.onOpen();
            }
        } else {
            mContentEditText.postDelayed(mContentTextDelayRunnable, 200);
        }
    }

    private Runnable mContentTextDelayRunnable = new Runnable() {

        @Override
        public void run() {
            mExpressionBtn.setNormalImageResId(R.drawable.ic_chat_expression_normal);
            mExpressionBtn.setPressedImageResId(R.drawable.ic_chat_expression_pressed);
            UIUtil.requestFocus(mContentEditText);
            UIUtil.showSoftInput(mContext, mContentEditText);
            mExpressionPanel.postDelayed(mExpressionPanelDelayRunnable, 200);
        }
    };

    private Runnable mExpressionPanelDelayRunnable = new Runnable() {

        @Override
        public void run() {
            mExpressionPanel.setVisibility(GONE);
            mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
    };

    public void hide() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mExpressionBtn.setNormalImageResId(R.drawable.ic_chat_expression_normal);
        mExpressionBtn.setPressedImageResId(R.drawable.ic_chat_expression_pressed);
        mExpressionPanel.setVisibility(GONE);
        UIUtil.loseFocus(mContentEditText);
        UIUtil.hideSoftInput(mContext, mContentEditText);
    }

    private OnExpressionPanelOpenListener mOnExpressionPanelOpenListener;
    public void setOnExpressionPanelOpenListener(OnExpressionPanelOpenListener listener) {
        this.mOnExpressionPanelOpenListener = listener;
    }

    public interface OnExpressionPanelOpenListener {
        void onOpen();
    }
}
