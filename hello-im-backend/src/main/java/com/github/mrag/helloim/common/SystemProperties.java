package com.github.mrag.helloim.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 系统参数
 */
@Component
@ConfigurationProperties(prefix = "system.properties")
public final class SystemProperties {

    /**
     * 错误码
     */
    private Errors errors;

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    public static class Errors {

        /**
         * token过期
         */
        private String tokenExpiration;
        /**
         * token缺失
         */
        private String tokenMissing;
        /**
         * token非法
         */
        private String tokenInvalidation;
        /**
         * 请求参数异常
         */
        private String badRequest;
        /**
         * 登录失败
         */
        private String loginFail;
        /**
         * 系统错误
         */
        private String internalServerError;
        /**
         * 未找到相应枚举类型
         */
        private String enumNotFound;

        public String getTokenExpiration() {
            return tokenExpiration;
        }

        public void setTokenExpiration(String tokenExpiration) {
            this.tokenExpiration = tokenExpiration;
        }

        public String getTokenMissing() {
            return tokenMissing;
        }

        public void setTokenMissing(String tokenMissing) {
            this.tokenMissing = tokenMissing;
        }

        public String getTokenInvalidation() {
            return tokenInvalidation;
        }

        public void setTokenInvalidation(String tokenInvalidation) {
            this.tokenInvalidation = tokenInvalidation;
        }

        public String getBadRequest() {
            return badRequest;
        }

        public void setBadRequest(String badRequest) {
            this.badRequest = badRequest;
        }

        public String getLoginFail() {
            return loginFail;
        }

        public void setLoginFail(String loginFail) {
            this.loginFail = loginFail;
        }

        public String getInternalServerError() {
            return internalServerError;
        }

        public void setInternalServerError(String internalServerError) {
            this.internalServerError = internalServerError;
        }

        public String getEnumNotFound() {
            return enumNotFound;
        }

        public void setEnumNotFound(String enumNotFound) {
            this.enumNotFound = enumNotFound;
        }
    }
}
