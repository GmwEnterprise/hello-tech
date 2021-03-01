package com.github.mrag.helloim.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

// 用户A对用户B的消息权限：[1]接收消息, [2]屏蔽消息, [3]特别关心
public enum ActionState implements EnumInterface {

    ReceiveMessage(1, "接收消息"),

    BlockMessage(2, "屏蔽消息"),

    SpecialConcern(3, "特别关心");

    private final int value;
    private final String name;

    ActionState(int value, String name) {
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
    public static ActionState find(int value) {
        return EnumInterface.find(values(), value);
    }
}