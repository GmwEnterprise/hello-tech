package com.github.mrag.helloim.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MessageStatus implements EnumInterface {

    AlreadySend(1, "消息已发送"),

    SendFailed(2, "消息发送失败"),

    AlreadyReceived(3, "消息已接收");

    private final int value;
    private final String name;

    MessageStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }

    @JsonCreator
    public static MessageStatus find(int value) {
        return EnumInterface.find(values(), value);
    }
}
