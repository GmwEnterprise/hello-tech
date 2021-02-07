package com.github.mrag.helloim.common;

import org.springframework.util.StringUtils;

public final class Asserts {

    public static void notEmpty(String val) {
        if (StringUtils.isEmpty(val)) {
            throw Exceptions.parameterIsEmpty();
        }
    }
}
