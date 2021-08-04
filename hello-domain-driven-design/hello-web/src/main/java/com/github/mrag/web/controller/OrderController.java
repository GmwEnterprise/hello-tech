package com.github.mrag.web.controller;

import com.github.mrag.app.OrderHandleService;
import com.github.mrag.app.dto.OrderSubmitDTO;
import com.github.mrag.web.common.Resp;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderHandleService orderHandleService;

    public OrderController(OrderHandleService orderHandleService) {
        this.orderHandleService = orderHandleService;
    }

    @PostMapping("/submit")
    public Resp submit(@RequestBody @Validated OrderSubmitDTO submitDTO) {
        orderHandleService.submitOrder(submitDTO);
        return Resp.ok();
    }
}
