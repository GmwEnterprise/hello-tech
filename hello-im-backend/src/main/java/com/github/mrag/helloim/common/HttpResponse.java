package com.github.mrag.helloim.common;

public final class HttpResponse {
    private String code;
    private String message;
    private Object body;

    public static HttpResponse ok(Object body) {
        return new HttpResponse("0000", "success", body);
    }

    public static HttpResponse fail(Exceptions.GlobalException exception) {
        return new HttpResponse(exception.getCode(), exception.getMessage(), null);
    }

    public HttpResponse(String code, String message, Object body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
