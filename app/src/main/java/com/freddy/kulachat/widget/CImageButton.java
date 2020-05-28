package com.freddy.kulachat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.freddy.kulachat.R;

import androidx.annotation.DrawableRes;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/28 17:08
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class CImageButton extends AppCompatImageView {

    private int normalImageResId;
    private int pressedImageResId;
    private int disabledImageResId;

    public CImageButton(Context context) {
        this(context, null);
    }

    public CImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CImageButton, defStyleAttr, 0);
        if (array != null) {
            normalImageResId = array.getResourceId(R.styleable.CImageButton_normal_image_res_id, 0);
            pressedImageResId = array.getResourceId(R.styleable.CImageButton_pressed_image_res_id, 0);
            disabledImageResId = array.getResourceId(R.styleable.CImageButton_disabled_image_res_id, 0);

            array.recycle();
        }

        init();
    }

    private void init() {
        setClickable(true);
        setImageResource(normalImageResId);
        setOnTouchListener(mOnTouchListener);
        setScaleType(ScaleType.CENTER_INSIDE);
    }

    public void setNormalImageResId(@DrawableRes int resId) {
        this.normalImageResId = resId;
        setImageResource(resId);
    }

    public void setPressedImageResId(@DrawableRes int resId) {
        this.pressedImageResId = resId;
    }

    public void setDisabledImageResId(@DrawableRes int resId) {
        this.disabledImageResId = resId;
        setImageResource(resId);
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
                    setImageResource(pressedImageResId);
                    break;
                }

                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL: {
                    setImageResource(normalImageResId);
                    break;
                }
            }
            return false;
        }
    };

    @Override
    public void setEnabled(boolean enabled) {
        if (enabled) {
            setImageResource(normalImageResId);
        } else {
            setImageResource(disabledImageResId);
        }
        super.setEnabled(enabled);
    }
}
