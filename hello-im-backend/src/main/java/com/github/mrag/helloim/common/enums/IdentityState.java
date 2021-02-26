package com.github.mrag.helloim.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.mrag.helloim.common.Exceptions;

import java.util.Arrays;

// 用户B对于用户A的身份：[1]好友, [2]黑名单
public enum IdentityState implements EnumInterface {

    Friend(1, "好友"),

    BlackList(2, "黑名单");

    private final int value;
    private final String name;

    IdentityState(int value, String name) {
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
    public static IdentityState find(int value) {
        return Arrays.stream(values())
                     .filter(item -> item.value == value)
                     .findFirst()
                     .orElseThrow(() -> Exceptions.enumNotFound(IdentityState.class, value));
    }
}


