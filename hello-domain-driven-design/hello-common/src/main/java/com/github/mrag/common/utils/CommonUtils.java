package com.github.mrag.common.utils;

import com.github.mrag.common.DomainPrimitive;
import com.github.mrag.common.Identifier;

import java.util.Objects;

public final class CommonUtils {

    public static <R> R returnNvl(R obj, R another) {
        return obj != null ? obj : another;
    }

    public static <T> boolean equals(T a, T b) {
        System.out.println("Equals: a = " + a);
        System.out.println("        b = " + b);
        System.out.println("     type = " + a.getClass());
        System.out.println("     type = " + a.getClass().getSuperclass());

        boolean res = equalsInternal(a, b);
        System.out.println("         => " + res);
        return res;
    }

    public static <T> boolean equalsInternal(T a, T b) {

        if (a == b) {
            return true;
        }

        if (a == null || b == null) {
            return false;
        }

        Class<?> type = a.getClass();
        if (Identifier.class.isAssignableFrom(type)) {
            return Objects.equals(((Identifier<?>) a).getId(), ((Identifier<?>) b).getId());
        }
        if (DomainPrimitive.class.isAssignableFrom(type)) {
            return Objects.equals(((DomainPrimitive<?>) a).getValue(), ((DomainPrimitive<?>) b).getValue());
        }
        return Objects.equals(a, b);
    }
}
