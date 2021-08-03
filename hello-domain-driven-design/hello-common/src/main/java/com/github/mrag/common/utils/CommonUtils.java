package com.github.mrag.common.utils;

public final class CommonUtils {
    public static <R> R returnNvl(R obj, R another) {
        return obj != null ? obj : another;
    }
}
