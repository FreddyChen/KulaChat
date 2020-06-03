package com.freddy.kulachat.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.freddy.kulachat.utils.DensityUtil;

/**
 * @author FreddyChen
 * @name
 * @date 2020/06/03 11:28
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class KeyboardStatePopupWindow extends PopupWindow implements ViewTreeObserver.OnGlobalLayoutListener {

    private Context mContext;

    public KeyboardStatePopupWindow(Context context, View anchorView) {
        this.mContext = context;
        View contentView = new View(context);
        setContentView(contentView);
        setWidth(0);
        setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        setInputMethodMode(INPUT_METHOD_NEEDED);
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(this);

        anchorView.post(() -> showAtLocation(anchorView, Gravity.NO_GRAVITY, 0, 0));
    }

    private int maxHeight = 0;
    private boolean isSoftKeyboardOpened;

    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();
        getContentView().getWindowVisibleDisplayFrame(rect);
        if (rect.bottom > maxHeight) {
            maxHeight = rect.bottom;
        }
        int screenHeight = DensityUtil.getScreenHeight(mContext);
        //键盘的高度
        int keyboardHeight = maxHeight - rect.bottom;
        boolean visible = keyboardHeight > screenHeight / 4;
        if (!isSoftKeyboardOpened && visible) {
            isSoftKeyboardOpened = true;
            if(mOnKeyboardStateListener != null) {
                mOnKeyboardStateListener.onOpened();
            }
        } else if (isSoftKeyboardOpened && !visible) {
            isSoftKeyboardOpened = false;
            if(mOnKeyboardStateListener != null) {
                mOnKeyboardStateListener.onClosed();
            }
        }
    }

    public void release() {
        getContentView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    private OnKeyboardStateListener mOnKeyboardStateListener;

    public void setOnKeyboardStateListener(OnKeyboardStateListener listener) {
        this.mOnKeyboardStateListener = listener;
    }
    public interface OnKeyboardStateListener {
        void onOpened();
        void onClosed();
    }
}
