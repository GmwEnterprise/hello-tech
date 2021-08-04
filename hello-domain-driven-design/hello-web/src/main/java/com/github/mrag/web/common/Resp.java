package com.github.mrag.web.common;

import lombok.Getter;

public final class Resp {
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
        return new Resp("200", "ok", null);
    }

    public static Resp ok(Object data) {
        return new Resp("200", "ok", data);
    }

    public static Resp error(String code, String msg) {
        return new Resp(code, msg, null);
    }
}
