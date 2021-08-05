package com.github.mrag.app;

import com.github.mrag.app.dto.InfoOrderDetailDTO;
import com.github.mrag.types.OrderId;

public interface OrderSearchService {

    InfoOrderDetailDTO findOne(OrderId orderId);
}
