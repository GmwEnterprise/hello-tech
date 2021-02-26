package com.github.mrag.helloim.domain;

import com.github.mrag.helloim.common.enums.ActionState;
import com.github.mrag.helloim.common.enums.IdentityState;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ImUserRelationship implements Serializable {
    private Integer id;

    private Integer userFrom;

    private Integer userTo;

    private IdentityState identityState;

    private ActionState actionState;

    private LocalDateTime lastUpdate;

    private LocalDateTime whenCreate;

    private Boolean end;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(Integer userFrom) {
        this.userFrom = userFrom;
    }

    public Integer getUserTo() {
        return userTo;
    }

    public void setUserTo(Integer userTo) {
        this.userTo = userTo;
    }

    public IdentityState getIdentityState() {
        return identityState;
    }

    public void setIdentityState(IdentityState identityState) {
        this.identityState = identityState;
    }

    public ActionState getActionState() {
        return actionState;
    }

    public void setActionState(ActionState actionState) {
        this.actionState = actionState;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public LocalDateTime getWhenCreate() {
        return whenCreate;
    }

    public void setWhenCreate(LocalDateTime whenCreate) {
        this.whenCreate = whenCreate;
    }

    public Boolean getEnd() {
        return end;
    }

    public void setEnd(Boolean end) {
        this.end = end;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userFrom=").append(userFrom);
        sb.append(", userTo=").append(userTo);
        sb.append(", identityState=").append(identityState);
        sb.append(", actionState=").append(actionState);
        sb.append(", lastUpdate=").append(lastUpdate);
        sb.append(", whenCreate=").append(whenCreate);
        sb.append(", end=").append(end);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}