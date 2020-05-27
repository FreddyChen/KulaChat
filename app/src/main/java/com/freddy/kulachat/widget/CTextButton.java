package com.freddy.kulachat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.freddy.kulachat.R;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/28 00:20
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class CTextButton extends AppCompatTextView {

    private int normalTextColor;
    private int pressedTextColor;
    private int disabledTextColor;

    public CTextButton(Context context) {
        this(context, null);
    }

    public CTextButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CTextButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CTextButton, defStyleAttr, 0);
        if (array != null) {
            normalTextColor = array.getColor(R.styleable.CTextButton_normal_text_color, ContextCompat.getColor(context, R.color.c_333333));
            pressedTextColor = array.getColor(R.styleable.CTextButton_pressed_text_color, ContextCompat.getColor(context, R.color.c_666666));
            disabledTextColor = array.getColor(R.styleable.CTextButton_disabled_text_color, ContextCompat.getColor(context, R.color.c_999999));

            array.recycle();
        }

        init();
    }

    private void init() {
        setClickable(true);
        setTextColor(normalTextColor);
        setOnTouchListener(mOnTouchListener);
    }

    private OnTouchListener mOnTouchListener = new OnTouchListener() {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (!isEnabled()) {
                return false;
            }

            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN: {
                    setTextColor(pressedTextColor);
                    break;
                }

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: {
                    setTextColor(normalTextColor);
                    break;
                }
            }
            return false;
        }
    };

    @Override
    public void setEnabled(boolean enabled) {
        if(enabled) {
            setTextColor(normalTextColor);
        }else {
            setTextColor(disabledTextColor);
        }
        super.setEnabled(enabled);
    }
}
