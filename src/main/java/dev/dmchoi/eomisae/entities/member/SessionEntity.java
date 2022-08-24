package dev.dmchoi.eomisae.entities.member;

import dev.dmchoi.eomisae.interfaces.IEntity;

import java.util.Date;

public class SessionEntity implements IEntity<SessionEntity> {
    public static SessionEntity build() {
        return new SessionEntity();
    }

    private Date createdAt;

    private Date updatedAt;

    private Date expiresAt;

    private boolean isExpired;

    private int userIndex;

    private String key;

    private String ua;

    public Date getCreatedAt() {
        return createdAt;
    }

    public SessionEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public SessionEntity setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public SessionEntity setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public SessionEntity setExpired(boolean expired) {
        isExpired = expired;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public SessionEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public String getKey() {
        return key;
    }

    public SessionEntity setKey(String key) {
        this.key = key;
        return this;
    }

    public String getUa() {
        return ua;
    }

    public SessionEntity setUa(String ua) {
        this.ua = ua;
        return this;
    }

    @Override
    public SessionEntity clone() {
        SessionEntity sessionEntity = new SessionEntity();
        sessionEntity.createdAt = this.createdAt;
        sessionEntity.updatedAt = this.updatedAt;
        sessionEntity.expiresAt = this.expiresAt;
        sessionEntity.isExpired = this.isExpired;
        sessionEntity.userIndex = this.userIndex;
        sessionEntity.key = this.key;
        sessionEntity.ua = this.ua;
        return sessionEntity;
    }

    @Override
    public void copyValuesOf(SessionEntity sessionEntity) {
        this.createdAt = sessionEntity.createdAt;
        this.updatedAt = sessionEntity.updatedAt;
        this.expiresAt = sessionEntity.expiresAt;
        this.isExpired = sessionEntity.isExpired;
        this.userIndex = sessionEntity.userIndex;
        this.key = sessionEntity.key;
        this.ua = sessionEntity.ua;

    }
}