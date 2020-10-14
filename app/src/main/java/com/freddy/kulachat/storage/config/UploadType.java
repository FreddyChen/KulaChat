package com.freddy.kulachat.storage.config;

public enum UploadType {
    /**
     * 普通上传
     */
    Normal,

    /**
     * 分片上传
     */
    Multipart,

    /**
     * 断点续传
     */
    Breakpoint
}
