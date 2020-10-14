package com.freddy.kulachat.media;

import android.net.Uri;

public class Media {

    private int id;
    private String displayName;
    private long fileSize;
    private int width;
    private int height;
    private String mimeType;
    private long time;
    private String folderName;
    private Uri uri;

    public Media(int id, String displayName, long fileSize, int width, int height, String mimeType, long time, String folderName, Uri uri) {
        this.id = id;
        this.displayName = displayName;
        this.fileSize = fileSize;
        this.width = width;
        this.height = height;
        this.mimeType = mimeType;
        this.time = time;
        this.folderName = folderName;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", fileSize=" + fileSize +
                ", width=" + width +
                ", height=" + height +
                ", mimeType='" + mimeType + '\'' +
                ", time=" + time +
                ", folderName='" + folderName + '\'' +
                ", uri=" + uri +
                '}';
    }
}
