package com.github.mrag.mvc;

public final class Resp {

    public static Resp ok() {
        return new Resp(200, "success", null);
    }

    public static Resp ok(Object body) {
        return new Resp(200, "success", body);
    }

    public static Resp fail(String message) {
        return new Resp(500, message, null);
    }

    private final int code;
    private final String message;
    private final Object body;

    public Resp(int code, String message, Object body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getBody() {
        return body;
    }
}
