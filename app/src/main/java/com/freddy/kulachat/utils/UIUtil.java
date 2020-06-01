package com.freddy.kulachat.utils;

import android.content.Context;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/28 01:03
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class UIUtil {

    /**
     * 使EditText获取焦点
     *
     * @param editText
     */
    public static void requestFocus(EditText editText) {
        if (editText != null) {
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.requestFocus();
        }
    }

    /**
     * 使EditText失去焦点
     *
     * @param editText
     */
    public static void loseFocus(EditText editText) {
        if (editText != null) {
            ViewGroup parent = (ViewGroup) editText.getParent();
            if (parent != null) {
                parent.setFocusable(true);
                parent.setFocusableInTouchMode(true);
                parent.requestFocus();
            } else {
                editText.setFocusable(false);
                editText.setFocusableInTouchMode(false);
            }
        }
    }

    /**
     * 是否应该隐藏键盘
     *
     * @param v
     * @param event
     * @return
     */
    public static boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 隐藏键盘
     *
     * @param context
     * @param v       输入框
     */
    public static void hideSoftInput(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
    }

    public static void showSoftInput(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, 0);
    }

    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        Resources res = context.getResources();
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = res.getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }
}
