package dev.dmchoi.eomisae.entities.system;

import java.util.Date;

public class ExceptionLogEntity {

    public static ExceptionLogEntity build() {
        return new ExceptionLogEntity();
    }
    private long index;
    private Date createdAt;
    private String message;
    private String stacktrace;

    public long getIndex() {
        return index;
    }

    public ExceptionLogEntity setIndex(long index) {
        this.index = index;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ExceptionLogEntity setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ExceptionLogEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getStacktrace() {
        return stacktrace;
    }

    public ExceptionLogEntity setStacktrace(String stacktrace) {
        this.stacktrace = stacktrace;
        return this;
    }
}
