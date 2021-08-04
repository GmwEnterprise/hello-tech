package com.github.mrag.domain.service;

import com.github.mrag.domain.aggregate.InfoOrder;
import com.github.mrag.domain.aggregate.entity.InfoOrderItem;

import java.util.List;

public interface InfoOrderService {

    void orderCombination(InfoOrder order, List<InfoOrderItem> items);
}
