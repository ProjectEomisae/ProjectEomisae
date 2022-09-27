package dev.dmchoi.eomisae.entities.member;

import dev.dmchoi.eomisae.interfaces.IEntity;

import java.util.Date;

public class ModifyUserEmailVerificationCodeEntity implements IEntity<ModifyUserEmailVerificationCodeEntity> {

    public static ModifyUserEmailVerificationCodeEntity build() {
        return new ModifyUserEmailVerificationCodeEntity();
    }
    private int index;
    private Date createdAt;
    private Date expiresAt;
    private boolean isExpired;
    private String code;
    private String salt;
    private int userIndex;
    private String modifyEmail;

    public int getIndex() {
        return index;
    }

    public ModifyUserEmailVerificationCodeEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ModifyUserEmailVerificationCodeEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public ModifyUserEmailVerificationCodeEntity setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public ModifyUserEmailVerificationCodeEntity setExpired(boolean expired) {
        isExpired = expired;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ModifyUserEmailVerificationCodeEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public ModifyUserEmailVerificationCodeEntity setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public ModifyUserEmailVerificationCodeEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public String getModifyEmail() {
        return modifyEmail;
    }

    public ModifyUserEmailVerificationCodeEntity setModifyEmail(String modifyEmail) {
        this.modifyEmail = modifyEmail;
        return this;
    }

    @Override
    public ModifyUserEmailVerificationCodeEntity clone() {
        ModifyUserEmailVerificationCodeEntity userEmailVerificationCodeEntity = new ModifyUserEmailVerificationCodeEntity();
        userEmailVerificationCodeEntity.index = this.index;
        userEmailVerificationCodeEntity.createdAt = this.createdAt;
        userEmailVerificationCodeEntity.expiresAt = this.expiresAt;
        userEmailVerificationCodeEntity.isExpired = this.isExpired;
        userEmailVerificationCodeEntity.code = this.code;
        userEmailVerificationCodeEntity.salt = this.salt;
        userEmailVerificationCodeEntity.userIndex = this.userIndex;
        userEmailVerificationCodeEntity.modifyEmail = this.modifyEmail;
        return userEmailVerificationCodeEntity;
    }

    @Override
    public void copyValuesOf(ModifyUserEmailVerificationCodeEntity userEmailVerificationCodeEntity) {
        this.index = userEmailVerificationCodeEntity.index;
        this.createdAt = userEmailVerificationCodeEntity.createdAt;
        this.expiresAt = userEmailVerificationCodeEntity.expiresAt;
        this.isExpired = userEmailVerificationCodeEntity.isExpired;
        this.code = userEmailVerificationCodeEntity.code;
        this.salt = userEmailVerificationCodeEntity.salt;
        this.userIndex = userEmailVerificationCodeEntity.userIndex;
        this.modifyEmail = userEmailVerificationCodeEntity.modifyEmail;
    }
}
