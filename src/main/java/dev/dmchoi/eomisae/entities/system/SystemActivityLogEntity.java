package dev.dmchoi.eomisae.entities.system;

import java.util.Date;

public class SystemActivityLogEntity {

    public static SystemActivityLogEntity build() {
        return new SystemActivityLogEntity();
    }
    private long index;
    private Date createdAt;
    private String clientIp;
    private String clientUa;
    private String requestUri;
    private String result;

    public long getIndex() {
        return index;
    }

    public SystemActivityLogEntity setIndex(long index) {
        this.index = index;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public SystemActivityLogEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getClientIp() {
        return clientIp;
    }

    public SystemActivityLogEntity setClientIp(String clientIp) {
        this.clientIp = clientIp;
        return this;
    }

    public String getClientUa() {
        return clientUa;
    }

    public SystemActivityLogEntity setClientUa(String clientUa) {
        this.clientUa = clientUa;
        return this;
    }

    public String getRequestUri() {
        return requestUri;
    }

    public SystemActivityLogEntity setRequestUri(String requestUri) {
        this.requestUri = requestUri;
        return this;
    }

    public String getResult() {
        return result;
    }

    public SystemActivityLogEntity setResult(String result) {
        this.result = result;
        return this;
    }
}
