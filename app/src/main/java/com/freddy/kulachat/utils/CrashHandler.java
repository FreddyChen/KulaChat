package com.freddy.kulachat.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.alibaba.fastjson.JSONObject;
import com.freddy.kulachat.KulaApp;
import com.freddy.kulachat.net.RequestManagerFactory;
import com.freddy.kulachat.net.config.DingDingResponseModel;
import com.freddy.kulachat.net.config.RequestMethod;
import com.freddy.kulachat.net.config.RequestOptions;
import com.freddy.kulachat.net.config.ServerType;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import io.reactivex.Observable;
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
        boolean existsCrashInfo = FileUtil.isFileExists(KulaApp.getInstance(), Environment.DIRECTORY_DOCUMENTS, CRASH_FOLDER_NAME, CRASH_FILE_NAME);
        if (!existsCrashInfo) {
            return;
        }

        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            String crashInfo = FileUtil.readContentFromSDCard(KulaApp.getInstance(), CRASH_FOLDER_NAME, CRASH_FILE_NAME);
            if (StringUtil.isEmpty(crashInfo)) {
                emitter.onNext(null);
            } else {
                emitter.onNext(crashInfo);
            }
            emitter.onComplete();
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<String>() {

            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                this.disposable = d;
            }

            @Override
            public void onNext(String s) {
                if (StringUtil.isEmpty(s)) {
                    return;
                }

                Map<String, Object> params = new HashMap<>();
                params.put("msgtype", "text");
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("content", "KulaChat\t我又崩溃啦！！！\n" + s);
                params.put("text", jsonObj);
                RequestOptions requestOptions = new RequestOptions.Builder()
                        .setBaseUrl("https://oapi.dingtalk.com/")
                        .setFunction("robot/send?access_token=b6f3d488eabb64a2ba6e2f571ef69045a91b7c5df6eec7df91a63ebf566c12ce")
                        .setMethod(RequestMethod.POST)
                        .setParams(params)
                        .build();
                Observable<DingDingResponseModel> observable = RequestManagerFactory.getRequestManager(ServerType.DINGDING).request(requestOptions);
                observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<DingDingResponseModel>() {

                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscribe() d = " + d);
                        this.disposable = d;
                    }

                    @Override
                    public void onNext(DingDingResponseModel responseModel) {
                        Log.d(TAG, "onNext() responseModel = " + responseModel);
                        if(responseModel.getCode() == 0) {
                            boolean isDelete = FileUtil.deleteFile(KulaApp.getInstance(), Environment.DIRECTORY_DOCUMENTS, CRASH_FOLDER_NAME, CRASH_FILE_NAME);
                            if(isDelete) {
                                Log.d(TAG, "crash文件删除成功");
                            }else {
                                Log.w(TAG, "crash文件删除失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError() e = " + e);
                        if (disposable != null & !disposable.isDisposed()) {
                            disposable.dispose();
                        }
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete()");
                    }
                });
            }

            @Override
            public void onError(Throwable e) {
                if (disposable != null & !disposable.isDisposed()) {
                    disposable.dispose();
                }
            }

            @Override
            public void onComplete() {

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

    @SuppressLint("SimpleDateFormat")
    private void saveCrashInfoToSDCard(Thread t, Throwable e) {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
//            Writer writer = new StringWriter();
//            PrintWriter printWriter = new PrintWriter(writer);
//            e.printStackTrace(printWriter);
//            emitter.onNext(writer.toString());

            StringBuilder sb = new StringBuilder();
            sb.append(getAppInfo(KulaApp.getInstance()));
            sb.append("线程信息：").append("thread_id:").append(t.getId()).append("\t").append("thread_name:").append(t.getName()).append("\n");
            sb.append("崩溃时间：").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n");
            sb.append("手机系统：").append(Build.VERSION.RELEASE).append("\n");
            sb.append("手机型号：").append(Build.MODEL).append("\n");
            String crashInfo = Log.getStackTraceString(e);
            if(crashInfo.contains("Caused by:")) {
                crashInfo = crashInfo.substring(crashInfo.indexOf("Caused by:"));
                sb.append("崩溃信息：").append("\n").append(crashInfo);
            }else {
                sb.append("崩溃信息：").append("\n").append(Log.getStackTraceString(e));
            }
            emitter.onNext(sb.toString());
            emitter.onComplete();
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Observer<String>() {

            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                this.disposable = d;
            }

            @SuppressLint("SimpleDateFormat")
            @Override
            public void onNext(String s) {
                FileUtil.saveContentToSDCard(KulaApp.getInstance(), CRASH_FOLDER_NAME, CRASH_FILE_NAME, s, false);
            }

            @Override
            public void onError(Throwable e) {
                if (disposable != null & !disposable.isDisposed()) {
                    disposable.dispose();
                }
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
