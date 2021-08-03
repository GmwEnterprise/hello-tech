package com.github.mrag.common;

import java.io.Serializable;

public abstract class DomainPrimitive<T> implements Serializable {

    public abstract T getValue();

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
