package com.github.mrag.repository.converter;

import com.github.mrag.domain.aggregate.InfoOrder;
import com.github.mrag.repository.persistence.InfoOrderDO;
import com.github.mrag.types.OrderId;
import com.github.mrag.types.Price;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {OrderId.class, Price.class})
public interface InfoOrderConverter {

    @Mapping(target = "orderId", source = "entity.orderId.id")
    @Mapping(target = "totalPrice", source = "entity.totalPrice.amount")
    InfoOrderDO entityToData(InfoOrder entity);

    @Mapping(target = "orderId", expression = "java(new OrderId(data.getOrderId()))")
    @Mapping(target = "totalPrice", expression = "java(new Price(data.getTotalPrice()))")
    InfoOrder dataToEntity(InfoOrderDO data);
}
