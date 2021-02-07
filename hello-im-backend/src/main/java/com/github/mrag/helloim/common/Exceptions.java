package com.github.mrag.helloim.common;

public final class Exceptions {

    public static GlobalException tokenExpiration() {
        return new GlobalException("0001", "令牌已过期");
    }

    public static GlobalException tokenMissing() {
        return new GlobalException("0002", "请携带令牌访问");
    }

    public static GlobalException parameterIsEmpty() {
        return new GlobalException("0003", "请求参数为空");
    }

    public static GlobalException signInError(String message) {
        return new GlobalException("0004", message);
    }

    public static class GlobalException extends RuntimeException {
        private final String code;

        public GlobalException(String code, String message) {
            super(String.format("code:[%s], message:[%s]", code, message));
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
