package dev.dmchoi.eomisae.entities.system;

import java.util.Date;

public class SystemExceptionLogEntity {

    public static SystemExceptionLogEntity build() {
        return new SystemExceptionLogEntity();
    }
    private long index;
    private Date createdAt;
    private String message;
    private String stacktrace;

    public long getIndex() {
        return index;
    }

    public SystemExceptionLogEntity setIndex(long index) {
        this.index = index;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public SystemExceptionLogEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public SystemExceptionLogEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public SystemExceptionLogEntity setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
        return this;
    }
}
