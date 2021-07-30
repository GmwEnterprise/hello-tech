package com.github.mrag.domain.aggregate.entity;

import com.github.mrag.common.Entity;
import com.github.mrag.types.OrderId;
import com.github.mrag.types.OrderItemId;
import com.github.mrag.types.Price;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InfoOrderItem implements Entity<OrderItemId> {

    private OrderItemId orderItemId;
    private OrderId orderId;
    private Price price;
    private Integer itemId;

    @Override
    public OrderItemId getId() {
        return orderItemId;
    }
}
