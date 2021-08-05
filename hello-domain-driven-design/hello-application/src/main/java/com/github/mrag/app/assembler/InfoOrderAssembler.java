package com.github.mrag.app.assembler;

import com.github.mrag.app.dto.InfoOrderDetailDTO;
import com.github.mrag.app.dto.OrderSubmitDTO;
import com.github.mrag.domain.aggregate.InfoOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InfoOrderAssembler {

    @Mapping(target = "customerName", source = "customer")
    @Mapping(target = "items", ignore = true)
    InfoOrder fromSubmit(OrderSubmitDTO submitDTO);

    @Mapping(target = "orderId", source = "orderId.id")
    @Mapping(target = "totalPrice", source = "totalPrice.amount")
    @Mapping(target = "currency", source = "totalPrice.currency")
    @Mapping(target = "items", ignore = true)
    InfoOrderDetailDTO toDetail(InfoOrder order);
}
