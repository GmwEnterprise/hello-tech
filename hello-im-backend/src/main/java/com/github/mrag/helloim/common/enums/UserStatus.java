package com.github.mrag.helloim.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.github.mrag.helloim.common.Exceptions;

import java.util.Arrays;

public enum UserStatus implements EnumInterface {

    Normal(1, "账户正常使用"),

    Forbidden(2, "账户被封禁");

    private final int value;
    private final String name;

    UserStatus(int value, String name) {
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
    public static UserStatus find(int value) {
        return Arrays.stream(values())
                     .filter(item -> item.value == value)
                     .findFirst()
                     .orElseThrow(() -> Exceptions.enumNotFound(UserStatus.class, value));
    }
}
