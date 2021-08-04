package com.github.mrag.domain.aggregate.entity;

import com.github.mrag.common.Entity;
import com.github.mrag.types.Item;
import com.github.mrag.types.OrderId;
import com.github.mrag.types.OrderItemId;
import com.github.mrag.types.Money;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InfoOrderItem implements Entity<OrderItemId> {

    private OrderItemId orderItemId;
    private OrderId orderId;
    private Item item;

    @Override
    public OrderItemId getId() {
        return orderItemId;
    }
}
