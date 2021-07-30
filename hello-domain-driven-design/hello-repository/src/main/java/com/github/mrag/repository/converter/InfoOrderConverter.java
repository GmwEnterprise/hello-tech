package com.github.mrag.repository.converter;

import com.github.mrag.domain.aggregate.InfoOrder;
import com.github.mrag.repository.persistence.InfoOrderDO;

public interface InfoOrderConverter {

    InfoOrderDO entityToData(InfoOrder entity);

    InfoOrder dataToEntity(InfoOrderDO data);
}
