package com.github.mrag.repository.converter;

import com.github.mrag.domain.aggregate.entity.InfoOrderItem;
import com.github.mrag.repository.persistence.InfoOrderItemDO;
import com.github.mrag.types.OrderId;
import com.github.mrag.types.OrderItemId;
import com.github.mrag.types.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {OrderId.class, OrderItemId.class, Price.class})
public interface InfoOrderItemConverter {

    @Mapping(target = "orderItemId", source = "entity.orderItemId.id")
    @Mapping(target = "orderId", source = "entity.orderId.id")
    @Mapping(target = "price", source = "entity.price.amount")
    InfoOrderItemDO entityToData(InfoOrderItem entity);

    @Mapping(target = "orderItemId", expression = "java(new OrderItemId(data.getOrderItemId()))")
    @Mapping(target = "orderId", expression = "java(new OrderId(data.getOrderId()))")
    @Mapping(target = "price", expression = "java(new Price(data.getPrice()))")
    InfoOrderItem dataToEntity(InfoOrderItemDO data);
}
