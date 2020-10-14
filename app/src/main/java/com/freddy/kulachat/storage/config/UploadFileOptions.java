package com.freddy.kulachat.storage.config;

public class UploadFileOptions {

    private String filePath;
    private UploadType uploadType;
    private UploadFileType uploadFileType;
    private UploadFolderType uploadFolderType;

    public UploadFileOptions(String filePath, UploadType uploadType, UploadFileType uploadFileType, UploadFolderType uploadFolderType) {
        this.filePath = filePath;
        this.uploadType = uploadType;
        this.uploadFileType = uploadFileType;
        this.uploadFolderType = uploadFolderType;
    }

    public String getFilePath() {
        return filePath;
    }

    public UploadType getUploadType() {
        return uploadType;
    }

    public UploadFileType getUploadFileType() {
        return uploadFileType;
    }

    public UploadFolderType getUploadFolderType() {
        return uploadFolderType;
    }

    @Override
    public String toString() {
        return "UploadFileOptions{" +
                "filePath='" + filePath + '\'' +
                ", uploadType=" + uploadType +
                ", uploadFileType=" + uploadFileType +
                ", uploadFolderType=" + uploadFolderType +
                '}';
    }

    public static class Builder {
        private String filePath;
        private UploadType uploadType;
        private UploadFileType uploadFileType;
        private UploadFolderType uploadFolderType;

        public Builder() {
            uploadType = UploadType.Normal;
            uploadFolderType = UploadFolderType.Defalut;
        }

        public Builder setFilePath(String filePath) {
            this.filePath = filePath;
            return this;
        }

        public Builder setUploadType(UploadType uploadType) {
            this.uploadType = uploadType;
            return this;
        }

        public Builder setUploadFileType(UploadFileType uploadFileType) {
            this.uploadFileType = uploadFileType;
            return this;
        }

        public Builder setUploadFolderType(UploadFolderType uploadFolderType) {
            this.uploadFolderType = uploadFolderType;
            return this;
        }

        public UploadFileOptions build() {
            return new UploadFileOptions(filePath, uploadType, uploadFileType, uploadFolderType);
        }
    }
}
