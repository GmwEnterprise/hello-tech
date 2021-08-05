package com.github.mrag.web.controller;

import com.github.mrag.app.OrderHandleService;
import com.github.mrag.app.OrderSearchService;
import com.github.mrag.app.dto.OrderSubmitDTO;
import com.github.mrag.types.OrderId;
import com.github.mrag.web.common.Resp;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderHandleService orderHandleService;
    private final OrderSearchService orderSearchService;

    public OrderController(OrderHandleService orderHandleService,
                           OrderSearchService orderSearchService) {
        this.orderHandleService = orderHandleService;
        this.orderSearchService = orderSearchService;
    }

    @PostMapping("/submit")
    public Resp submit(@RequestBody @Validated OrderSubmitDTO submitDTO) {
        OrderId orderId = orderHandleService.submitOrder(submitDTO);
        return Resp.ok(Map.of("orderId", orderId.getId().toString()));
    }

    @GetMapping("/main/{orderId}")
    public Resp findOrder(@PathVariable String orderId) {
        try {
            long id = Long.parseLong(orderId);
            return Resp.ok(orderSearchService.findOne(new OrderId(id)));
        } catch (NumberFormatException e) {
            return Resp.error(Resp.NOT_FOUND, "订单号[" + orderId + "]查询结果为空");
        }
    }
}
