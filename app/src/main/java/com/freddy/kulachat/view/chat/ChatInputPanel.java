package com.freddy.kulachat.view.chat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.freddy.kulachat.R;
import com.freddy.kulachat.utils.DensityUtil;
import com.freddy.kulachat.utils.UIUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
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
        ButterKnife.bind(this, view);
        init();
    }

    private void init() {
        setGravity(Gravity.BOTTOM);
        setPadding(DensityUtil.dp2px(mContext, 10), DensityUtil.dp2px(mContext, 6), DensityUtil.dp2px(mContext, 10), DensityUtil.dp2px(mContext, 6));
        setBackgroundColor(ContextCompat.getColor(mContext, R.color.c_77ffffff));
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
                break;
            }
            case R.id.btn_more: {
                break;
            }
        }
    }

    @OnEditorAction(R.id.et_content)
    boolean onContentEditTextEditorAction(int actionId) {
        if(actionId == EditorInfo.IME_ACTION_SEND) {
            Toasty.success(mContext, "发送", Toasty.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public EditText getEditText() {
        return mContentEditText;
    }

    public void hide() {
        UIUtil.loseFocus(mContentEditText);
        UIUtil.hideSoftInput(mContext, mContentEditText);
    }
}
