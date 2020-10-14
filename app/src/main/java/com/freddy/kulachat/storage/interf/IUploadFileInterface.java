package com.freddy.kulachat.storage.interf;

import com.freddy.kulachat.storage.config.UploadFileOptions;
import com.freddy.kulachat.storage.listener.OnUploadFileListener;

public interface IUploadFileInterface {

    void uploadFile(UploadFileOptions options, OnUploadFileListener listener);
}
