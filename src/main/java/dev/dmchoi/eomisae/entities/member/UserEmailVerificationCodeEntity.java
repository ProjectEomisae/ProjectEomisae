package dev.dmchoi.eomisae.entities.member;

import java.util.Date;

public class UserEmailVerificationCodeEntity {

    public static UserEmailVerificationCodeEntity build() {
        return new UserEmailVerificationCodeEntity();
    }
    private int index;
    private Date createdAt;
    private Date expiresAt;
    private boolean isExpired;
    private String code;
    private String salt;
    private int userIndex;

    public int getIndex() {
        return index;
    }

    public UserEmailVerificationCodeEntity setIndex(int index) {
        this.index = index;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public UserEmailVerificationCodeEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public UserEmailVerificationCodeEntity setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public UserEmailVerificationCodeEntity setExpired(boolean expired) {
        isExpired = expired;
        return this;
    }

    public String getCode() {
        return code;
    }

    public UserEmailVerificationCodeEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public String getSalt() {
        return salt;
    }

    public UserEmailVerificationCodeEntity setSalt(String salt) {
        this.salt = salt;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public UserEmailVerificationCodeEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }
}
