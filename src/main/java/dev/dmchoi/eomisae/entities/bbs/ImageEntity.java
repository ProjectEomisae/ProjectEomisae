package dev.dmchoi.eomisae.entities.bbs;

import dev.dmchoi.eomisae.interfaces.IEntity;

import java.util.Date;

public class ImageEntity implements IEntity<ImageEntity> {
    public static ImageEntity build() {
        return new ImageEntity();
    }
    private String id;
    private Date createdAt;
    private String fileName;
    private String fileType;
    private byte[] data;

    public ImageEntity() {
    }
    public ImageEntity(String id, Date createdAt, String fileName, String fileType, byte[] data) {
        this.id = id;
        this.createdAt = createdAt;
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public ImageEntity setId(String id) {
        this.id = id;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ImageEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public ImageEntity setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getFileType() {
        return fileType;
    }

    public ImageEntity setFileType(String fileType) {
        this.fileType = fileType;
        return this;
    }

    public byte[] getData() {
        return data;
    }

    public ImageEntity setData(byte[] data) {
        this.data = data;
        return this;
    }

    @Override
    public ImageEntity clone() {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.id = this.id;
        imageEntity.createdAt = this.createdAt;
        imageEntity.fileName = this.fileName;
        imageEntity.fileType = this.fileType;
        imageEntity.data = this.data;
        return imageEntity;
    }

    @Override
    public void copyValuesOf(ImageEntity imageEntity) {
        this.id = imageEntity.id;
        this.createdAt= imageEntity.createdAt;
        this.fileName = imageEntity.fileName;
        this.fileType = imageEntity.fileType;
        this.data = imageEntity.data;
    }
}
