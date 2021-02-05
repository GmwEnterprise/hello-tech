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
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", messageStatus=").append(messageStatus);
        sb.append(", receiveTime=").append(receiveTime);
        sb.append(", sendUserId=").append(sendUserId);
        sb.append(", receiveUserId=").append(receiveUserId);
        sb.append(", textContent=").append(textContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}