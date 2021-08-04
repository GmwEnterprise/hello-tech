package com.github.mrag.repository.converter;

import com.github.mrag.common.NullableOperation;
import com.github.mrag.domain.aggregate.InfoOrder;
import com.github.mrag.repository.persistence.InfoOrderDO;
import com.github.mrag.types.Money;
import com.github.mrag.types.OrderId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {OrderId.class, Money.class})
public interface InfoOrderConverter extends NullableOperation {

    @Mapping(target = "orderId", source = "entity.orderId.id")
    @Mapping(target = "totalPrice", source = "entity.totalPrice.amount")
    InfoOrderDO entityToData(InfoOrder entity);

    @Mapping(target = "orderId", expression = "java(nullableSet(OrderId.class, data.getOrderId()))")
    @Mapping(target = "totalPrice", expression = "java(nullableSet(Money.class, data.getTotalPrice()))")
    InfoOrder dataToEntity(InfoOrderDO data);
}
