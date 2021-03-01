package com.github.mrag.helloim.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.mrag.helloim.common.Exceptions;

import java.util.Arrays;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface EnumInterface {

    String getName();

    int getValue();

    static <EnumType extends EnumInterface> EnumType find(EnumType[] values, int value) {
        return Arrays.stream(values)
                     .filter(item -> item.getValue() == value)
                     .findFirst()
                     .orElseThrow(() -> Exceptions.enumNotFound(values.getClass().getComponentType(), value));
    }
}
