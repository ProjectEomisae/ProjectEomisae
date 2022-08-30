package dev.dmchoi.eomisae.entities.member;

import dev.dmchoi.eomisae.interfaces.IEntity;

import java.util.Date;

public class ProfileImageEntity implements IEntity<ProfileImageEntity> {

    public static ProfileImageEntity build() {
        return new ProfileImageEntity();
    }
    private String id;
    private byte[]  data;

    public String getId() {
        return id;
    }

    public ProfileImageEntity setId(String id) {
        this.id = id;
        return this;
    }

    public byte[] getData() {
        return data;
    }

    public ProfileImageEntity setData(byte[] data) {
        this.data = data;
        return this;
    }

    @Override
    public ProfileImageEntity clone() {
        ProfileImageEntity profileImageEntity = new ProfileImageEntity();
        profileImageEntity.id = this.id;
        profileImageEntity.data = this.data;
        return profileImageEntity;
    }

    @Override
    public void copyValuesOf(ProfileImageEntity profileImageEntity) {
        this.id = profileImageEntity.id;
        this.data = profileImageEntity.data;
    }
}
