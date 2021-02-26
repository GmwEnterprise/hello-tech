package com.github.mrag.helloim.common.enums;

import com.github.mrag.helloim.common.Asserts;
import com.github.mrag.helloim.common.Exceptions;

import java.util.Arrays;

public final class EnumOperator {

    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> T find(Class<T> type, int value) {
        Asserts.implementsEnumInterface(type);
        return (T) Arrays.stream(type.getEnumConstants())
                         .map(source -> ((EnumInterface) source))
                         .filter(item -> item.getValue() == value)
                         .findFirst().orElseThrow(() -> Exceptions.enumNotFound(type, value));
    }
}
