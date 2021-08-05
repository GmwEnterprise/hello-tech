package com.github.mrag.repository.converter;

import com.github.mrag.common.NullableOperation;
import com.github.mrag.domain.aggregate.entity.InfoOrderItem;
import com.github.mrag.repository.persistence.InfoOrderItemDO;
import com.github.mrag.types.Item;
import com.github.mrag.types.Money;
import com.github.mrag.types.OrderId;
import com.github.mrag.types.OrderItemId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {OrderId.class, OrderItemId.class, Money.class, Item.class})
public interface InfoOrderItemConverter extends NullableOperation {

    @Mapping(target = "orderItemId", source = "orderItemId.id")
    @Mapping(target = "orderId", source = "orderId.id")
    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "price", source = "item.itemPrice.amount")
    InfoOrderItemDO entityToData(InfoOrderItem entity);

    @Mapping(target = "orderItemId", expression = "java(nullableSet(OrderItemId.class, data.getOrderItemId()))")
    @Mapping(target = "orderId", expression = "java(nullableSet(OrderId.class, data.getOrderId()))")
    @Mapping(target = "item", expression = "java(Item.findNullableItem(data.getItemId()))")
    InfoOrderItem dataToEntity(InfoOrderItemDO data);
}
