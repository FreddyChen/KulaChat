package com.freddy.kulachat.storage.config;

import com.freddy.kulachat.storage.oss.OSSConfig;

import java.io.File;

public class OSSUploadFile {

    private String filePath;
    private String bucketName;
    private String objectKey;
    private String endpoint;
    private String uploadUrl;

    public OSSUploadFile(String filePath, String objectKey) {
        this.filePath = filePath;
        this.bucketName = OSSConfig.BUCKET_NAME;
        this.objectKey = objectKey;
        this.endpoint = OSSConfig.ENDPOINT;
        this.uploadUrl = OSSConfig.PREFIX_HTTP + OSSConfig.BUCKET_NAME + "." + OSSConfig.ENDPOINT + File.separator + objectKey;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getObjectKey() {
        return objectKey;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    @Override
    public String toString() {
        return "OSSUploadFile{" +
                "filePath='" + filePath + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", objectKey='" + objectKey + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", uploadUrl='" + uploadUrl + '\'' +
                '}';
    }
}
