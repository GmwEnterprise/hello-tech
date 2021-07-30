package com.github.mrag.types;

import com.github.mrag.common.Identifier;
import lombok.Value;

@Value
public class OrderId implements Identifier {

    Long id;
}
