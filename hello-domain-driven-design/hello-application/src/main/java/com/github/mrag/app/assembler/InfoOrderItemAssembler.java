package com.github.mrag.app.assembler;

import com.github.mrag.app.dto.InfoOrderItemDetailDTO;
import com.github.mrag.app.dto.OrderItemSubmitDTO;
import com.github.mrag.domain.aggregate.entity.InfoOrderItem;
import com.github.mrag.types.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = {Item.class})
public interface InfoOrderItemAssembler {

    @Mapping(target = "item", expression = "java(Item.findItem(submitDTO.getItemId()))")
    InfoOrderItem fromSubmit(OrderItemSubmitDTO submitDTO);

    @Mapping(target = "orderItemId", source = "orderItemId.id")
    @Mapping(target = "price", source = "item.itemPrice.amount")
    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "itemName", expression = "java(order.getItem().name())")
    InfoOrderItemDetailDTO toDetail(InfoOrderItem order);
}
