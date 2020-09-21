package com.freddy.kulachat.view;

import android.util.Log;

import androidx.core.app.ActivityCompat;
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

    private static final Stack<BaseActivity> ACTIVITY_STACK = new Stack<>();

    /**
     * 添加activity到栈
     * @param activity
     */
    public void addActivityToStack(BaseActivity activity) {
        ACTIVITY_STACK.add(activity);
        Log.d(TAG, "addActivityToStack() activity = " + activity + ", size = " + ACTIVITY_STACK.size());
    }

    /**
     * 从栈中移除activity
     * @param activity
     */
    public void removeActivityFromStack(BaseActivity activity) {
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

        BaseActivity activity = getTopActivityInStack();
        if(activity == null) {
            return;
        }

        this.finishActivity(activity);
    }

    /**
     * 结束指定的activity
     * @param activity
     */
    public void finishActivity(BaseActivity activity) {
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

        for(BaseActivity activity : ACTIVITY_STACK) {
            if(activity.getClass().equals(cls)) {
                ACTIVITY_STACK.remove(activity);
                if(!activity.isFinishing()) {
                    if(activity.hasTransition()) {
                        ActivityCompat.finishAfterTransition(activity);
                    }else {
                        activity.finish();
                    }
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
    public BaseActivity getTopActivityInStack() {
        if(ACTIVITY_STACK.isEmpty()) {
            return null;
        }

        return ACTIVITY_STACK.lastElement();
    }

    /**
     * 结束除指定activity外的其他activity
     * @param activity
     */
    public void finishOtherActivity(BaseActivity activity) {
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

        for(BaseActivity activity : ACTIVITY_STACK) {
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

        for(BaseActivity activity : ACTIVITY_STACK) {
            finishActivity(activity);
        }
        Log.d(TAG, "finishAllActivity(), size = " + ACTIVITY_STACK.size());
    }
}
