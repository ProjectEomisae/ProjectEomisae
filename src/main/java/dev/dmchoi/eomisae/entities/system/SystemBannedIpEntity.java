package dev.dmchoi.eomisae.entities.system;

import java.util.Date;

public class SystemBannedIpEntity {

    public static SystemBannedIpEntity build() {
        return new SystemBannedIpEntity();
    }
    private long index;
    private Date createdAt;
    private Date expiresAt;
    private boolean isExpired;
    private String ip;

    public long getIndex() {
        return index;
    }

    public SystemBannedIpEntity setIndex(long index) {
        this.index = index;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public SystemBannedIpEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public SystemBannedIpEntity setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
        return this;
    }

    public boolean isExpired() {
        return isExpired;
    }

    public SystemBannedIpEntity setExpired(boolean expired) {
        isExpired = expired;
        return this;
    }

    public String getIp() {
        return ip;
    }

    public SystemBannedIpEntity setIp(String ip) {
        this.ip = ip;
        return this;
    }
}
