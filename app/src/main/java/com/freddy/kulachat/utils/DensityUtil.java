package com.freddy.kulachat.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/28 17:54
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class DensityUtil {

    public static DisplayMetrics metrics;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue
     * @return
     */
    public static int dp2px(float dpValue) {
        return Math.round(dpValue * metrics.density);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     *
     * @param pxValue
     * @return
     */
    public static int px2dp(float pxValue) {
        return Math.round(pxValue / metrics.density);
    }

    /**
     * sp转px
     *
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics()));
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * 获取屏幕宽度
     * @return
     */
    public static int getScreenWidth() {
        return metrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     * @return
     */
    public static int getScreenHeight() {
        return metrics.heightPixels;
    }

    /**
     * 获取像素密度
     * @return
     */
    public static float getDensity() {
        return metrics.density;
    }
}
