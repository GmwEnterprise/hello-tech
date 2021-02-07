package com.github.mrag.helloim.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Exceptions {
    private static final Logger log = LoggerFactory.getLogger(Exceptions.class);

    public static GlobalException tokenExpiration() {
        return new GlobalException("0001", "令牌已过期");
    }

    public static GlobalException tokenMissing() {
        return new GlobalException("0002", "请携带令牌访问");
    }

    public static GlobalException parameterIsEmpty(String parameterName) {
        return new GlobalException("0003", "请求参数为空:[" + parameterName + "]");
    }

    public static GlobalException signInError(String message) {
        return new GlobalException("0004", message);
    }

    public static GlobalException fromSystem(String message) {
        GlobalException exp = new GlobalException("0005", "系统错误，详见日志");
        log.error(message, exp);
        return exp;
    }

    public static <T extends Enum<T>> GlobalException enumNotFound(Class<T> type, int value) {
        String message = String.format("未找到相应枚举值:[%s(%d)]", type.getSimpleName(), value);
        GlobalException exp = new GlobalException("0006", message);
        log.error(message, exp);
        return exp;
    }

    public static GlobalException tokenInvalidation() {
        return new GlobalException("0007", "无效的令牌");
    }

    public static GlobalException badRequest(String message) {
        return new GlobalException("0008", message);
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
