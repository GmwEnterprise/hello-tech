package com.github.mrag.app.assembler;

import com.github.mrag.app.dto.OrderSubmitDTO;
import com.github.mrag.domain.aggregate.InfoOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InfoOrderAssembler {

    @Mapping(target = "items", ignore = true)
    @Mapping(target = "customerName", source = "customer")
    InfoOrder fromSubmit(OrderSubmitDTO submitDTO);
}
