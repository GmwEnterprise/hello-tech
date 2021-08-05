package com.github.mrag.app.impl;

import com.github.mrag.app.OrderSearchService;
import com.github.mrag.app.assembler.InfoOrderAssembler;
import com.github.mrag.app.assembler.InfoOrderItemAssembler;
import com.github.mrag.app.dto.InfoOrderDetailDTO;
import com.github.mrag.app.dto.InfoOrderItemDetailDTO;
import com.github.mrag.domain.aggregate.InfoOrder;
import com.github.mrag.domain.repository.InfoOrderRepository;
import com.github.mrag.types.OrderId;
import com.github.mrag.types.errortype.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderSearchServiceImpl implements OrderSearchService {

    private final InfoOrderRepository orderRepo;
    private final InfoOrderAssembler orderAssembler;
    private final InfoOrderItemAssembler orderItemAssembler;

    public OrderSearchServiceImpl(InfoOrderRepository orderRepo,
                                  InfoOrderAssembler orderAssembler,
                                  InfoOrderItemAssembler orderItemAssembler) {
        this.orderRepo = orderRepo;
        this.orderAssembler = orderAssembler;
        this.orderItemAssembler = orderItemAssembler;
    }

    @Override
    public InfoOrderDetailDTO findOne(OrderId orderId) {
        InfoOrder order = orderRepo.find(orderId);
        if (order == null) {
            throw new NotFoundException("订单号[" + orderId + "]查询结果为空");
        }
        InfoOrderDetailDTO detailDTO = orderAssembler.toDetail(order);
        List<InfoOrderItemDetailDTO> items = order
                .getItems().stream().map(orderItemAssembler::toDetail)
                .collect(Collectors.toList());
        detailDTO.setItems(items);
        return detailDTO;
    }
}
