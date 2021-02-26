package com.github.mrag.helloim.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.mrag.helloim.common.Exceptions;

import java.util.Arrays;

// 消息应答情况：[0]非应答消息, [1]未应答， [2]已应答
public enum AnswerStatus implements EnumInterface {

    NonResponseMessage(0, "非应答消息"),

    NoResponse(1, "未应答"),

    Answered(2, "已应答");

    private final int value;
    private final String name;

    AnswerStatus(int value, String name) {
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
    public static AnswerStatus find(int value) {
        return Arrays.stream(values())
                     .filter(item -> item.value == value)
                     .findFirst()
                     .orElseThrow(() -> Exceptions.enumNotFound(AnswerStatus.class, value));
    }
}
