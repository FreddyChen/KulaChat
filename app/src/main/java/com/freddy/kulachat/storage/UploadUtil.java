package com.freddy.kulachat.storage;

import com.freddy.kulachat.storage.config.UploadFileOptions;
import com.freddy.kulachat.storage.listener.OnUploadFileListener;

public class UploadUtil {

    public static void uploadFile(UploadFileOptions options, OnUploadFileListener listener) {
        UploadFileManagerFactory.getUploadFileManager().uploadFile(options, listener);
    }
}
