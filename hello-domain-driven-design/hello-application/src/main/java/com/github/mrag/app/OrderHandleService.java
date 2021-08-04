package com.github.mrag.app;

import com.github.mrag.app.dto.OrderSubmitDTO;
import com.github.mrag.app.dto.OrderUpdateDTO;

public interface OrderHandleService {

    void submitOrder(OrderSubmitDTO submitDTO);

    void updateOrder(OrderUpdateDTO submitDTO);
}
