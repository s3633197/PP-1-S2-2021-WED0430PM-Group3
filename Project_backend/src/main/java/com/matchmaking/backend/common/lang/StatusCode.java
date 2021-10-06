package com.matchmaking.backend.common.lang;

public enum StatusCode {
    SUCCESS(200,"OK"),
    BAD(400,"Bad Request"),
    CREATE(201,"Created"),
    UNAUTHORIZED(401,"UnAuthorized"),
    FORBIDDEN(403,"ForBidden"),
    NOTFOUND(404,"Not Found"),
    ERROR(500,"Internal error");

    private long code;
    private String message;


    StatusCode(long code, String message) {
        this.code = code;
        this.message =message;

    }

    public long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
