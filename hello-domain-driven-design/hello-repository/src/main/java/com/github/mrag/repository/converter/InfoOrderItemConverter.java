package com.github.mrag.repository.converter;

import com.github.mrag.domain.aggregate.entity.InfoOrderItem;
import com.github.mrag.repository.persistence.InfoOrderItemDO;

public interface InfoOrderItemConverter {

    InfoOrderItemDO entityToData(InfoOrderItem entity);

    InfoOrderItemDO dataToEntity(InfoOrderItem infoOrderItem);
}
