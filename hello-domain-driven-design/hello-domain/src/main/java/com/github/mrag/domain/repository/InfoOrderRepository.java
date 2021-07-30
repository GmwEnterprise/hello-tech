package com.github.mrag.domain.repository;

import com.github.mrag.common.BaseRepository;
import com.github.mrag.domain.aggregate.InfoOrder;
import com.github.mrag.types.OrderId;

public interface InfoOrderRepository extends BaseRepository<InfoOrder, OrderId> {
}
