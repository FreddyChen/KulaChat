package com.freddy.kulachat.storage.config;

public enum UploadFileType {

    JPG_IMAGE(".jpg");

    private String suffix;

    UploadFileType(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }
}
