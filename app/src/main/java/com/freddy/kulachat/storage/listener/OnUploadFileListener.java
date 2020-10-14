package com.freddy.kulachat.storage.listener;

public interface OnUploadFileListener {

    void onStart(String filePath);

    void onProgress(String filePath, int progress);

    void onSucceed(String filePath, String fileUrl);

    void onFailed(String filePath, String errMsg);

    void onFinished();
}
