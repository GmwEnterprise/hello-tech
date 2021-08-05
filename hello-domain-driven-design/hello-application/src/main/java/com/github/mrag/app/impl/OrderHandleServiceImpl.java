package com.github.mrag.app.impl;

import com.github.mrag.app.OrderHandleService;
import com.github.mrag.app.assembler.InfoOrderAssembler;
import com.github.mrag.app.assembler.InfoOrderItemAssembler;
import com.github.mrag.app.dto.OrderSubmitDTO;
import com.github.mrag.app.dto.OrderUpdateDTO;
import com.github.mrag.domain.aggregate.InfoOrder;
import com.github.mrag.domain.aggregate.entity.InfoOrderItem;
import com.github.mrag.domain.repository.InfoOrderRepository;
import com.github.mrag.domain.service.InfoOrderService;
import com.github.mrag.types.OrderId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderHandleServiceImpl implements OrderHandleService {

    private final InfoOrderRepository orderRepo;
    private final InfoOrderService orderService;
    private final InfoOrderAssembler orderAssembler;
    private final InfoOrderItemAssembler orderItemAssembler;

    public OrderHandleServiceImpl(InfoOrderRepository orderRepo,
                                  InfoOrderService orderService,
                                  InfoOrderAssembler orderAssembler,
                                  InfoOrderItemAssembler orderItemAssembler) {
        this.orderRepo = orderRepo;
        this.orderService = orderService;
        this.orderAssembler = orderAssembler;
        this.orderItemAssembler = orderItemAssembler;
    }

    @Override
    public OrderId submitOrder(OrderSubmitDTO submitDTO) {
        InfoOrder order = orderAssembler.fromSubmit(submitDTO);
        List<InfoOrderItem> items = submitDTO
                .getItems().stream().map(orderItemAssembler::fromSubmit)
                .collect(Collectors.toList());

        // 合并主子单
        orderService.orderCombination(order, items);

        // 写入
        orderRepo.saveAndDetach(order);

        // 返回订单号
        return order.getOrderId();
    }

    @Override
    public void updateOrder(OrderUpdateDTO submitDTO) {

        // todo
    }
}
