package com.github.mrag.helloim.common;

import java.io.Serializable;
import java.time.LocalDateTime;

public class HttpToken implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final long EXPIRATION_HOURS = 2;
    public static final long BEFORE_EXPIRATION_MINUTES = 2 * 60 - 5;
    public static final String PERMISSION_HEADER_NAME = "authorization";

    private Integer userId;
    private String username;
    private LocalDateTime issuedAt;
    private LocalDateTime expiration;
    private LocalDateTime beforeExpiration;

    private boolean expState;

    public HttpToken() {
    }

    public HttpToken(Integer userId, String username) {
        this.userId = userId;
        this.username = username;
        this.issuedAt = LocalDateTime.now();
        this.expiration = LocalDateTime.now().plusHours(EXPIRATION_HOURS);
        this.beforeExpiration = LocalDateTime.now().plusMinutes(BEFORE_EXPIRATION_MINUTES);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public LocalDateTime getBeforeExpiration() {
        return beforeExpiration;
    }

    public void setBeforeExpiration(LocalDateTime beforeExpiration) {
        this.beforeExpiration = beforeExpiration;
    }

    public boolean getExpState() {
        return expState;
    }

    public void setExpState(boolean expState) {
        this.expState = expState;
    }
}
