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
    View mVoiceBtn;
    @BindView(R.id.btn_keyboard)
    View mKeyboardBtn;
    @BindView(R.id.btn_expression)
    View mExpressionBtn;
    @BindView(R.id.btn_more)
    View mMoreBtn;
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
                if (mExpressionPanel.getVisibility() == VISIBLE) {
                    showHideExpressionPanel(false);
                } else {
                    showHideExpressionPanel(true);
                }
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
        showHideExpressionPanel(!focused);
    }

    public EditText getEditText() {
        return mContentEditText;
    }

    private void showHideExpressionPanel(boolean isShow) {
        if(isShow) {
            UIUtil.loseFocus(mContentEditText);
            UIUtil.hideSoftInput(mContext, mContentEditText);
            mExpressionPanel.setVisibility(VISIBLE);
            mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        }else {
            mExpressionPanel.setVisibility(GONE);
            mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
    }

    public void hide() {
        UIUtil.loseFocus(mContentEditText);
        UIUtil.hideSoftInput(mContext, mContentEditText);
    }
}
