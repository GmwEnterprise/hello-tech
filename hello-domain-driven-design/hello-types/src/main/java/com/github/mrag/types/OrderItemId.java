package com.github.mrag.types;

import com.github.mrag.common.Identifier;
import lombok.Value;

@Value
public class OrderItemId implements Identifier<Long> {

    Long id;

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}
