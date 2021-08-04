package com.github.mrag.app.impl;

import com.github.mrag.app.OrderHandleService;
import com.github.mrag.app.assembler.InfoOrderAssembler;
import com.github.mrag.app.dto.OrderSubmitDTO;
import com.github.mrag.app.dto.OrderUpdateDTO;
import com.github.mrag.domain.aggregate.InfoOrder;
import com.github.mrag.domain.repository.InfoOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderHandleServiceImpl implements OrderHandleService {

    private final InfoOrderRepository orderRepo;
    private final InfoOrderAssembler orderAssembler;

    public OrderHandleServiceImpl(InfoOrderRepository orderRepo,
                                  InfoOrderAssembler orderAssembler) {
        this.orderRepo = orderRepo;
        this.orderAssembler = orderAssembler;
    }

    @Override
    public void submitOrder(OrderSubmitDTO submitDTO) {
        InfoOrder order = orderAssembler.fromSubmit(submitDTO);
        orderRepo.save(order);

        // todo
    }

    @Override
    public void updateOrder(OrderUpdateDTO submitDTO) {

        // todo
    }
}
