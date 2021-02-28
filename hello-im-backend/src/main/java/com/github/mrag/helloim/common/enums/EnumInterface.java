package com.github.mrag.helloim.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

// @JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface EnumInterface {

    String getName();

    int getValue();
}
