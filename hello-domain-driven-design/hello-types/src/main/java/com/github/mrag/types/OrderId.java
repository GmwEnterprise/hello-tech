package com.github.mrag.types;

import com.github.mrag.common.Identifier;
import lombok.NonNull;
import lombok.Value;

@Value
public class OrderId implements Identifier<Long> {

    @NonNull
    Long id;

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
