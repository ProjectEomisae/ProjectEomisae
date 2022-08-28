package dev.dmchoi.eomisae.entities.system;

import java.util.Date;

public class BannedIpEntity {

    public static BannedIpEntity build() {
        return new BannedIpEntity();
    }
    private long index;
    private Date createdAt;
    private Date expiresAt;
    private boolean isExpired;
    private String ip;

    public long getIndex() {
        return index;
    }

    public BannedIpEntity setIndex(long index) {
        this.index = index;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public BannedIpEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public BannedIpEntity setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public BannedIpEntity setExpired(boolean expired) {
        isExpired = expired;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public BannedIpEntity setIp(String ip) {
        this.ip = ip;
        return this;
    }
}
