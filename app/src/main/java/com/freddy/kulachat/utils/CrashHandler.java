package com.freddy.kulachat.utils;

import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import androidx.annotation.NonNull;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/21 17:19
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private CrashHandler() { }

    public static CrashHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CrashHandler INSTANCE = new CrashHandler();
    }

    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    public void init() {
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Log.e("CrashHandler", "t = " + t + ", exception = " + writer.toString());

        if(mDefaultExceptionHandler != null) {
            mDefaultExceptionHandler.uncaughtException(t, e);
        }

        System.exit(0);
    }
}
