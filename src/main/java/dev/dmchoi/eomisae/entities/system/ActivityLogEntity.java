package dev.dmchoi.eomisae.entities.system;

import java.util.Date;

public class ActivityLogEntity {

    public static ActivityLogEntity build() {
        return new ActivityLogEntity();
    }
    private long index;
    private int userIndex;
    private Date createdAt;
    private String clientIp;
    private String clientUa;
    private String requestUri;
    private String result;

    public long getIndex() {
        return index;
    }

    public ActivityLogEntity setIndex(long index) {
        this.index = index;
        return this;
    }

    public int getUserIndex() {
        return userIndex;
    }

    public ActivityLogEntity setUserIndex(int userIndex) {
        this.userIndex = userIndex;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ActivityLogEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getClientIp() {
        return clientIp;
    }

    public ActivityLogEntity setClientIp(String clientIp) {
        this.clientIp = clientIp;
        return this;
    }

    public String getClientUa() {
        return clientUa;
    }

    public ActivityLogEntity setClientUa(String clientUa) {
        this.clientUa = clientUa;
        return this;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public ActivityLogEntity setRequestUri(String requestUri) {
        this.requestUri = requestUri;
        return this;
    }

    public String getResult() {
        return result;
    }

    public ActivityLogEntity setResult(String result) {
        this.result = result;
        return this;
    }
}
