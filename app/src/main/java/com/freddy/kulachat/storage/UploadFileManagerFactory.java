package com.freddy.kulachat.storage;

import com.freddy.kulachat.storage.interf.IUploadFileInterface;
import com.freddy.kulachat.storage.oss.OSSUploadFileManager;

public class UploadFileManagerFactory {

    public static IUploadFileInterface getUploadFileManager() {
        return OSSUploadFileManager.getInstance();
    }
}
