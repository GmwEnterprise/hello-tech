package com.github.mrag.repository;

import com.github.mrag.common.diff.DiffUtils;
import com.github.mrag.common.diff.EntityDiff;
import com.github.mrag.domain.aggregate.InfoOrder;
import com.github.mrag.domain.aggregate.entity.InfoOrderItem;
import com.github.mrag.types.Item;
import com.github.mrag.types.Money;
import com.github.mrag.types.OrderId;
import com.github.mrag.types.OrderItemId;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

public class TestDiff implements Serializable {

    @Test
    public void testDiff() {
        InfoOrder order = new InfoOrder();
        order.setOrderId(new OrderId(1L));
        order.setCreateTime(LocalDateTime.now());
        order.setTotalPrice(new Money(BigDecimal.TEN));

        order.setItems(Arrays.asList(
                new InfoOrderItem() {{
                    setOrderItemId(new OrderItemId(1L));
                    setOrderId(new OrderId(1L));
                    setPrice(new Money(BigDecimal.valueOf(5L)));
                    setItem(Item.findItem(1));
                }},
                new InfoOrderItem() {{
                    setOrderItemId(new OrderItemId(2L));
                    setOrderId(new OrderId(1L));
                    setPrice(new Money(BigDecimal.valueOf(5L)));
                    setItem(Item.findItem(1));
                }}
        ));

        InfoOrder order2 = SerializationUtils.clone(order);

        System.out.println(order);
        System.out.println(order2);

        order2.getItems().get(1).setItem(Item.findItem(2));

        EntityDiff<InfoOrder, OrderId> diff = DiffUtils.diff(order, order2);

        System.out.println(diff);
    }

    @Test
    public void testAnonymousClass() throws Exception {
        InfoOrderItem item = new InfoOrderItem() {{
            setOrderItemId(new OrderItemId(2L));
            setOrderId(new OrderId(1L));
            setPrice(new Money(BigDecimal.valueOf(5L)));
            setItem(Item.findItem(1));
        }};

        System.out.println(item.getClass());
        System.out.println(item.getClass().getSuperclass());

        Field this$0 = item.getClass().getDeclaredField("this$0");
        Object x = this$0.get(item);
        System.out.println(this$0);
        System.out.println(x);
        System.out.println(x.getClass());
    }
}
