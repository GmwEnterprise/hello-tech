package com.github.mrag.app;

import com.github.mrag.app.dto.OrderSubmitDTO;
import com.github.mrag.app.dto.OrderUpdateDTO;
import com.github.mrag.types.OrderId;

public interface OrderHandleService {

    OrderId submitOrder(OrderSubmitDTO submitDTO);

    void updateOrder(OrderUpdateDTO submitDTO);
}
