package com.freddy.kulachat.view.chat;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.inputmethod.EditorInfo;

import com.freddy.kulachat.R;
import com.freddy.kulachat.utils.DensityUtil;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/02 21:57
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class ChatEditText extends AppCompatEditText {

    private Context mContext;

    public ChatEditText(Context context) {
        this(context, null);
    }

    public ChatEditText(Context context, AttributeSet attrs) {
        // 在自定义EditText继承EditText的构造器中，defStyleAttr不能设置为0，必须设置为android.R.attr.editTextStyle，否则会导致出现无法输入的问题
        // 详见StackOverflow @see https://blog.csdn.net/j236027367/article/details/88914755
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public ChatEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        setImeOptions(EditorInfo.IME_ACTION_SEND);
        setInputType(InputType.TYPE_CLASS_TEXT);
        setPadding(DensityUtil.dp2px(mContext, 8), DensityUtil.dp2px(mContext, 6), DensityUtil.dp2px(mContext, 8), DensityUtil.dp2px(mContext, 6));
        setTextColor(ContextCompat.getColor(mContext, R.color.c_000000));
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        setHorizontallyScrolling(false);
        setMaxLines(5);
        setBackground(null);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_UP) {
            Log.d("ChatEditText", "键盘向下");
            super.onKeyPreIme(keyCode, event);
            return false;
        }
        return super.onKeyPreIme(keyCode, event);
    }
}
