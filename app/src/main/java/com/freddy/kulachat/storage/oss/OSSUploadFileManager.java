package com.freddy.kulachat.storage.oss;

import android.util.Log;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.freddy.kulachat.entity.User;
import com.freddy.kulachat.model.user.UserManager;
import com.freddy.kulachat.storage.config.OSSUploadFile;
import com.freddy.kulachat.storage.config.UploadFileOptions;
import com.freddy.kulachat.storage.config.UploadFileType;
import com.freddy.kulachat.storage.config.UploadFolderType;
import com.freddy.kulachat.storage.config.UploadType;
import com.freddy.kulachat.storage.interf.IUploadFileInterface;
import com.freddy.kulachat.storage.listener.OnUploadFileListener;
import com.freddy.kulachat.utils.StringUtil;

import java.io.File;
import java.util.UUID;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OSSUploadFileManager implements IUploadFileInterface {

    private static final String TAG = OSSUploadFileManager.class.getSimpleName();
    private static volatile OSSUploadFileManager instance;
    private OSSClient ossClient;
    private User mCurrentUser;

    private OSSUploadFileManager() {
        Log.d(TAG, "OSSUploadFileManager()");
        ossClient = OSSClientManager.getInstance().getOSSClient();
        mCurrentUser = UserManager.getInstance().getCurrentUser();
    }

    public static OSSUploadFileManager getInstance() {
        if (instance == null) {
            synchronized (OSSUploadFileManager.class) {
                if (instance == null) {
                    instance = new OSSUploadFileManager();
                }
            }
        }

        return instance;
    }

    @Override
    public void uploadFile(UploadFileOptions options, OnUploadFileListener listener) {
        if (options == null) {
            if (listener != null) {
                listener.onFailed(null, "options is null");
            }
            Log.w(TAG, "uploadFile() failed, reason: options is null");
            return;
        }

        String filePath = options.getFilePath();
        if (StringUtil.isEmpty(filePath)) {
            if (listener != null) {
                listener.onFailed(null, "filePath is null or empty");
            }
            Log.w(TAG, "uploadFile() failed, reason: filePath is null or empty");
            return;
        }
        // 如果文件路径带有file:// 则替换成空字符串
        if (filePath.contains("file://")) {
            filePath = filePath.replace("file://", "");
        }

        UploadFileType uploadFileType = options.getUploadFileType();
        if (uploadFileType == null) {
            if (listener != null) {
                listener.onFailed(null, "uploadFileType is null");
            }
            Log.w(TAG, "uploadFile() failed, reason: uploadFileType is null");
            return;
        }

        UploadType uploadType = options.getUploadType();
        if (uploadType == null) {
            uploadType = UploadType.Normal;
        }

        UploadFolderType uploadFolderType = options.getUploadFolderType();
        if (uploadFileType == null) {
            uploadFolderType = UploadFolderType.Defalut;
        }

        String objectKey = null;
        switch (uploadFileType) {
            case JPG_IMAGE:
                if (uploadFolderType == UploadFolderType.Defalut) {
                    objectKey = uploadFolderType.getFolderType() + File.separator + UUID.randomUUID().toString() + uploadFileType.getSuffix();
                } else {
                    objectKey = String.format(uploadFolderType.getFolderType(), mCurrentUser.getUserId()) + File.separator + UUID.randomUUID().toString() + uploadFileType.getSuffix();
                }
                break;

            default:
                break;
        }


        if (StringUtil.isEmpty(objectKey)) {
            if (listener != null) {
                listener.onFailed(null, "objectKey is null or empty");
            }
            Log.w(TAG, "uploadFile() failed, reason: objectKey is null or empty");
            return;
        }

        OSSUploadFile uploadFile = new OSSUploadFile(filePath, objectKey);
        switch (uploadType) {
            case Multipart:
                break;

            case Breakpoint:
                break;

            case Normal:
            default:
                normalUpload(uploadFile, listener);
                break;
        }
    }

    private void normalUpload(OSSUploadFile uploadFile, OnUploadFileListener listener) {
        if (listener != null) {
            listener.onStart(uploadFile.getFilePath());
        }

        Observable.create(new ObservableOnSubscribe<Object>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Throwable {
                PutObjectRequest request = new PutObjectRequest(uploadFile.getBucketName(), uploadFile.getObjectKey(), uploadFile.getFilePath());
                request.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {

                    int lastProgress = 0;

                    @Override
                    public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                        int progress = (int) (currentSize * 1.0f / totalSize * 100);
                        if (progress != lastProgress && progress % 10 == 0) {
                            Log.d(TAG, "progress = " + progress);
                            emitter.onNext(progress);
                            lastProgress = progress;
                        }
                    }
                });

                ossClient.asyncPutObject(request, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {

                    @Override
                    public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                        emitter.onNext(null);
                        emitter.onComplete();
                    }

                    @Override
                    public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
                        String errorMsg = null;
                        // 请求异常
                        if (clientException != null) {
                            // 本地异常如网络异常等
                            errorMsg = clientException.getMessage();
                            clientException.printStackTrace();
                        } else if (serviceException != null) {
                            // 服务异常
                            errorMsg = serviceException.getMessage();
                            serviceException.printStackTrace();
                            Log.e(TAG, "normalUpload() onFailure[errorCode=" + serviceException.getErrorCode() + "]");
                            Log.e(TAG, "normalUpload() onFailure[requestId=" + serviceException.getRequestId() + "]");
                            Log.e(TAG, "normalUpload() onFailure[hostId=" + serviceException.getHostId() + "]");
                            Log.e(TAG, "normalUpload() onFailure[rawMessage=" + serviceException.getRawMessage() + "]");
                        }

                        Throwable t = null;
                        if (StringUtil.isNotEmpty(errorMsg)) {
                            t = new Throwable(errorMsg);
                        } else {
                            t = new Throwable("unknown exception.");
                        }

                        emitter.onError(t);
                        emitter.onComplete();
                    }
                });
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Object>() {

            private Disposable disposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                this.disposable = d;
            }

            @Override
            public void onNext(@NonNull Object obj) {
                if (obj != null) {
                    int progress = (int) obj;
                    if (listener != null) {
                        listener.onProgress(uploadFile.getFilePath(), progress);
                    }
                } else {
                    if (listener != null) {
                        listener.onSucceed(uploadFile.getFilePath(), uploadFile.getUploadUrl());
                    }
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                if (disposable != null && !disposable.isDisposed()) {
                    disposable.dispose();
                }
                disposable = null;
            }

            @Override
            public void onComplete() {
                if (listener != null) {
                    listener.onFinished();
                }
            }
        });
    }
}
