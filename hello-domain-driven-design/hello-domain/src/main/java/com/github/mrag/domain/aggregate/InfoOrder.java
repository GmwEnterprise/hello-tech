package com.github.mrag.domain.aggregate;

import com.github.mrag.common.Aggregate;
import com.github.mrag.domain.aggregate.entity.InfoOrderItem;
import com.github.mrag.types.OrderId;
import com.github.mrag.types.Price;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class InfoOrder implements Aggregate<OrderId> {

    private OrderId orderId;
    private Price totalPrice;
    private LocalDateTime createTime;
    private List<InfoOrderItem> items;

    @Override
    public OrderId getId() {
        return orderId;
    }
}
