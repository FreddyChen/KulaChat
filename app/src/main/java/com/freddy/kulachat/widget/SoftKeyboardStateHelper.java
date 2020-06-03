package com.freddy.kulachat.widget;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

import com.freddy.kulachat.utils.DensityUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/31 22:10
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class SoftKeyboardStateHelper implements ViewTreeObserver.OnGlobalLayoutListener {

    public interface SoftKeyboardStateListener {
        void onSoftKeyboardOpened(int keyboardHeight);

        void onSoftKeyboardClosed();
    }

    private final List<SoftKeyboardStateListener> listeners = new LinkedList<>();
    private final View activityRootView;
    private int lastSoftKeyboardHeightInPx;
    private boolean isSoftKeyboardOpened;

    public SoftKeyboardStateHelper(View activityRootView) {
        this(activityRootView, false);
    }

    public SoftKeyboardStateHelper(View activityRootView, boolean isSoftKeyboardOpened) {
        this.activityRootView = activityRootView;
        this.isSoftKeyboardOpened = isSoftKeyboardOpened;
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        final Rect r = new Rect();
        activityRootView.getWindowVisibleDisplayFrame(r);
        int screenHeight = DensityUtil.getScreenHeight(activityRootView.getContext());
        int heightDifference = screenHeight - r.bottom;
        boolean visible = heightDifference > screenHeight / 4;
        if (!isSoftKeyboardOpened && visible) {
            isSoftKeyboardOpened = true;
            notifyOnSoftKeyboardOpened(heightDifference);
        }else if(isSoftKeyboardOpened && !visible) {
            isSoftKeyboardOpened = false;
            notifyOnSoftKeyboardClosed();
        }

        //        final int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
        //        if (!isSoftKeyboardOpened && heightDiff > DIFF_HEIGHT) {
        //            isSoftKeyboardOpened = true;
        //            Log.d("SoftKeyboardStateHelper", "键盘打开，软键盘高度 = " + heightDiff);
        //            notifyOnSoftKeyboardOpened(heightDiff);
        //        } else if (isSoftKeyboardOpened && heightDiff < DIFF_HEIGHT) {
        //            isSoftKeyboardOpened = false;
        //            Log.d("SoftKeyboardStateHelper", "键盘收起" + heightDiff);
        //            notifyOnSoftKeyboardClosed();
        //        }

        //        int height = r.height();
        //        if (windowHeight == 0) {
        //            windowHeight = height;
        //        } else {
        //            keyboardHeight = windowHeight - height;
        //            if (windowHeight != height) {
        //                if (!isSoftKeyboardOpened && keyboardHeight > DIFF_HEIGHT) {
        //                    isSoftKeyboardOpened = true;
        //                    notifyOnSoftKeyboardOpened(keyboardHeight);
        //                }
        //            } else if (isSoftKeyboardOpened && keyboardHeight < DIFF_HEIGHT) {
        //                isSoftKeyboardOpened = false;
        //            }
        //        }
    }


    public void setIsSoftKeyboardOpened(boolean isSoftKeyboardOpened) {
        this.isSoftKeyboardOpened = isSoftKeyboardOpened;
    }

    public boolean isSoftKeyboardOpened() {
        return isSoftKeyboardOpened;
    }

    public int getLastSoftKeyboardHeightInPx() {
        return lastSoftKeyboardHeightInPx;
    }

    public void addSoftKeyboardStateListener(SoftKeyboardStateListener listener) {
        listeners.add(listener);
    }

    public void removeSoftKeyboardStateListener(SoftKeyboardStateListener listener) {
        listeners.remove(listener);
    }

    private void notifyOnSoftKeyboardOpened(int keyboardHeightInPx) {
        this.lastSoftKeyboardHeightInPx = keyboardHeightInPx;

        for (SoftKeyboardStateListener listener : listeners) {
            if (listener != null) {
                listener.onSoftKeyboardOpened(keyboardHeightInPx);
            }
        }
    }

    private void notifyOnSoftKeyboardClosed() {
        for (SoftKeyboardStateListener listener : listeners) {
            if (listener != null) {
                listener.onSoftKeyboardClosed();
            }
        }
    }

    public void release() {
        if (activityRootView != null) {
            activityRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        }
    }
}
