package com.github.mrag.app.assembler;

import com.github.mrag.app.dto.OrderSubmitDTO;
import com.github.mrag.domain.aggregate.InfoOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", imports = {})
public interface InfoOrderAssembler {

    InfoOrder fromSubmit(OrderSubmitDTO submitDTO);
}
