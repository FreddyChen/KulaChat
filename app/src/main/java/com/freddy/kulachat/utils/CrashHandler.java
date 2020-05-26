package com.freddy.kulachat.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.freddy.kulachat.KulaApp;
import com.freddy.kulachat.net.config.RequestMethod;
import com.freddy.kulachat.net.config.RequestOptions;
import com.freddy.kulachat.net.retrofit.RetrofitRequestManager;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/21 17:19
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @desc
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private CrashHandler() {
    }

    public static CrashHandler getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CrashHandler INSTANCE = new CrashHandler();
    }

    private static final String TAG = CrashHandler.class.getSimpleName();
    private static final String CRASH_FOLDER_NAME = "crash";
    private static final String CRASH_FILE_NAME = "crash.txt";
    private Thread.UncaughtExceptionHandler mDefaultExceptionHandler;

    public void init() {
        mDefaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);

        reportCrashInfoToDDRobot();
    }

    private void reportCrashInfoToDDRobot() {
        boolean existsCrashInfo = FileUtil.isExists(KulaApp.getInstance(), CRASH_FOLDER_NAME, CRASH_FILE_NAME);
        if (!existsCrashInfo) {
            return;
        }
        String crashInfo = FileUtil.readContentFromSDCard(KulaApp.getInstance(), CRASH_FOLDER_NAME, CRASH_FILE_NAME);
        if (StringUtil.isEmpty(crashInfo)) {
            return;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("msgtype", "text");
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("content", "KulaChat " + crashInfo);
        params.put("text", jsonObj);
        RequestOptions requestOptions = new RequestOptions.Builder()
                .setBaseUrl("https://oapi.dingtalk.com/")
                .setFunction("robot/send?access_token=b6f3d488eabb64a2ba6e2f571ef69045a91b7c5df6eec7df91a63ebf566c12ce")
                .setMethod(RequestMethod.POST)
                .setParams(params)
                .build();
        Observable observable = RetrofitRequestManager.getInstance().request(requestOptions);
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe() d = " + d);
            }

            @Override
            public void onNext(Object o) {
                Log.d(TAG, "onNext() o = " + o);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError() e = " + e);
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete()");
            }
        });
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        saveCrashInfoToSDCard(t, e);

        if (mDefaultExceptionHandler != null) {
            mDefaultExceptionHandler.uncaughtException(t, e);
        }
        System.exit(0);
    }

    private void saveCrashInfoToSDCard(Thread t, Throwable e) {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            e.printStackTrace(printWriter);
            emitter.onNext(writer.toString());
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @SuppressLint("SimpleDateFormat")
            @Override
            public void onNext(String s) {
                StringBuilder sb = new StringBuilder();
                sb.append(getAppInfo(KulaApp.getInstance()));
//                sb.append("崩溃时间：").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n");
                sb.append("手机系统：").append(Build.VERSION.RELEASE).append("\n");
                sb.append("手机型号：").append(Build.MODEL).append("\n");
                sb.append("崩溃信息：").append(s);
                FileUtil.saveContentToSDCard(KulaApp.getInstance(), CRASH_FOLDER_NAME, CRASH_FILE_NAME, sb.toString(), true);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    /**
     * 获取应用程序信息
     */
    public String getAppInfo(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return "应用包名：" + packageInfo.packageName + "\n应用版本：" + packageInfo.versionName + "\n";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
