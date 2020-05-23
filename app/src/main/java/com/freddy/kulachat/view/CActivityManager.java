package com.freddy.kulachat.view;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/24 02:08
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
public class CActivityManager {

    private static final String TAG = CActivityManager.class.getSimpleName();
    private CActivityManager() { }

    public static CActivityManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CActivityManager INSTANCE = new CActivityManager();
    }

    private static final Stack<Activity> ACTIVITY_STACK = new Stack<>();

    /**
     * 添加activity到栈
     * @param activity
     */
    public void addActivityToStack(Activity activity) {
        ACTIVITY_STACK.add(activity);
        Log.d(TAG, "addActivityToStack() activity = " + activity + ", size = " + ACTIVITY_STACK.size());
    }

    /**
     * 从栈中移除activity
     * @param activity
     */
    public void removeActivityFromStack(Activity activity) {
        if(ACTIVITY_STACK.isEmpty()) {
            return;
        }

        if(!ACTIVITY_STACK.contains(activity)) {
            return;
        }

        ACTIVITY_STACK.remove(activity);
        Log.d(TAG, "removeActivityFromStack() activity = " + activity + ", size = " + ACTIVITY_STACK.size());
    }

    /**
     * 结束当前activity
     */
    public void finishActivity() {
        if(ACTIVITY_STACK.isEmpty()) {
            return;
        }

        Activity activity = getTopActivityInStack();
        if(activity == null) {
            return;
        }

        this.finishActivity(activity);
    }

    /**
     * 结束指定的activity
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if(activity == null) {
            return;
        }

        this.finishActivity(activity.getClass());
    }

    /**
     * 结束指定的activity
     * @param cls
     */
    public void finishActivity(Class<?> cls) {
        if(ACTIVITY_STACK.isEmpty()) {
            return;
        }

        for(Activity activity : ACTIVITY_STACK) {
            if(activity.getClass().equals(cls)) {
                ACTIVITY_STACK.remove(activity);
                if(!activity.isFinishing()) {
                    activity.finish();
                }
                break;
            }
        }
        Log.d(TAG, "finishActivity() cls = " + cls + ", size = " + ACTIVITY_STACK.size());
    }

    /**
     * 获取栈顶activity
     * @return
     */
    public Activity getTopActivityInStack() {
        if(ACTIVITY_STACK.isEmpty()) {
            return null;
        }

        return ACTIVITY_STACK.lastElement();
    }

    /**
     * 结束除指定activity外的其他activity
     * @param activity
     */
    public void finishOtherActivity(Activity activity) {
        if(activity == null) {
            return;
        }

        this.finishActivity(activity.getClass());
    }

    /**
     * 结束除指定activity外的其他activity
     * @param cls
     */
    public void finishOtherActivity(Class<?> cls) {
        if(cls == null) {
            return;
        }

        if(ACTIVITY_STACK.isEmpty()) {
            return;
        }

        for(Activity activity : ACTIVITY_STACK) {
            if(activity == null) {
                continue;
            }
            if(activity.getClass().equals(cls)) {
                continue;
            }

            finishActivity(activity);
        }
        Log.d(TAG, "finishOtherActivity() cls = " + cls + ", size = " + ACTIVITY_STACK.size());
    }

    /**
     * 结束所有activity
     */
    public void finishAllActivity() {
        if(ACTIVITY_STACK.isEmpty()) {
            return;
        }

        for(Activity activity : ACTIVITY_STACK) {
            finishActivity(activity);
        }
        Log.d(TAG, "finishAllActivity(), size = " + ACTIVITY_STACK.size());
    }
}
