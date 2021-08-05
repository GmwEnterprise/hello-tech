package com.github.mrag.web.common;

import lombok.Getter;

public final class Resp {

    public static final String OK = "200";
    public static final String INTERNAL_ERROR = "500";
    public static final String NOT_FOUND = "404";
    public static final String BAD_REQUEST = "400";
    public static final String TIMEOUT = "502";

    @Getter
    private final String code, msg;

    @Getter
    private final Object data;

    public Resp(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Resp ok() {
        return new Resp(OK, "ok", null);
    }

    public static Resp ok(Object data) {
        return new Resp(OK, "ok", data);
    }

    public static Resp error(String code, String msg) {
        return new Resp(code, msg, null);
    }
}
