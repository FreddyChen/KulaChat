package com.freddy.kulachat.storage.config;

public enum UploadFolderType {

    Defalut("default"),
    AvatarImage("avatar_image/%1$s");

    private String folderType;

    UploadFolderType(String folderType) {
        this.folderType = folderType;
    }

    public String getFolderType() {
        return folderType;
    }
}
