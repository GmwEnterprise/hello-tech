package com.github.mrag.helloim.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ImUserMessage implements Serializable {
    private Integer id;

    private LocalDateTime sendTime;

    private Byte messageStatus;

    private LocalDateTime receiveTime;

    private Integer sendUserId;

    private Integer receiveUserId;

    private String textContent;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public Byte getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(Byte messageStatus) {
        this.messageStatus = messageStatus;
    }

    public LocalDateTime getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(Integer sendUserId) {
        this.sendUserId = sendUserId;
    }

    public Integer getReceiveUserId() {
        return receiveUserId;
    }

    public void setReceiveUserId(Integer receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " [" +
                "Hash = " + hashCode() +
                ", id=" + id +
                ", sendTime=" + sendTime +
                ", messageStatus=" + messageStatus +
                ", receiveTime=" + receiveTime +
                ", sendUserId=" + sendUserId +
                ", receiveUserId=" + receiveUserId +
                ", textContent=" + textContent +
                ", serialVersionUID=" + serialVersionUID +
                "]";
    }
}