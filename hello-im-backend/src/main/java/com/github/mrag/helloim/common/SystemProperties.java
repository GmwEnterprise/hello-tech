package com.github.mrag.helloim.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "im-sys")
public final class SystemProperties {

    @Component
    public static class ErrorCode {
        private String tokenExpiration;
        private String tokenMissing;
        private String tokenInvalidation;
        private String badRequest;
        private String emptyParameter;
        private String signInError;
        private String systemError;
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

        public String getEmptyParameter() {
            return emptyParameter;
        }

        public void setEmptyParameter(String emptyParameter) {
            this.emptyParameter = emptyParameter;
        }

        public String getSignInError() {
            return signInError;
        }

        public void setSignInError(String signInError) {
            this.signInError = signInError;
        }

        public String getSystemError() {
            return systemError;
        }

        public void setSystemError(String systemError) {
            this.systemError = systemError;
        }

        public String getEnumNotFound() {
            return enumNotFound;
        }

        public void setEnumNotFound(String enumNotFound) {
            this.enumNotFound = enumNotFound;
        }
    }
}
