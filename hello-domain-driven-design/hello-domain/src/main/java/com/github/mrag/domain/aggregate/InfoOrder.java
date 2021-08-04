package com.github.mrag.domain.aggregate;

import com.github.mrag.common.Aggregate;
import com.github.mrag.domain.aggregate.entity.InfoOrderItem;
import com.github.mrag.types.Money;
import com.github.mrag.types.OrderId;
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
    private Money totalPrice;
    private String customerName;
    private Integer subOrderCount;
    private LocalDateTime createTime;
    private LocalDateTime finishTime;
    private List<InfoOrderItem> items;

    @Override
    public OrderId getId() {
        return orderId;
    }
}
