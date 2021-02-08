package com.github.mrag.helloim.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Exceptions {
    private static final Logger log = LoggerFactory.getLogger(Exceptions.class);

    private static SystemProperties propertiesInst;

    private static SystemProperties getProps() {
        if (propertiesInst == null) {
            synchronized (Exceptions.class) {
                if (propertiesInst == null) {
                    propertiesInst = ComponentUtils.getBean(SystemProperties.class);
                }
            }
        }
        return propertiesInst;
    }

    public static GlobalException tokenExpiration() {
        return new GlobalException(getProps().getErrors().getTokenExpiration(), "令牌已过期");
    }

    public static GlobalException tokenMissing() {
        return new GlobalException(getProps().getErrors().getTokenMissing(), "请携带令牌访问");
    }

    public static GlobalException parameterIsEmpty(String parameterName) {
        return new GlobalException(getProps().getErrors().getBadRequest(),
                                   String.format("请求参数为空:[%s]", parameterName));
    }

    public static GlobalException signInError(String message) {
        return new GlobalException(getProps().getErrors().getLoginFail(), message);
    }

    public static GlobalException fromSystem(String message) {
        GlobalException exp = new GlobalException(getProps().getErrors().getInternalServerError(),
                                                  "系统错误，详见日志");
        log.error(message, exp);
        return exp;
    }

    public static <T extends Enum<T>> GlobalException enumNotFound(Class<T> type, int value) {
        String message = String.format("未找到相应枚举值:[%s(%d)]", type.getSimpleName(), value);
        GlobalException exp = new GlobalException(getProps().getErrors().getEnumNotFound(), message);
        log.error(message, exp);
        return exp;
    }

    public static GlobalException tokenInvalidation() {
        return new GlobalException(getProps().getErrors().getTokenInvalidation(), "无效的令牌");
    }

    public static GlobalException badRequest(String message) {
        return new GlobalException(getProps().getErrors().getBadRequest(), message);
    }

    /**
     * 全局统一异常
     */
    public static class GlobalException extends RuntimeException {
        private final String code;

        public GlobalException(String code, String message) {
            super(message);
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
