package com.github.mrag.domain.service.impl;

import com.github.mrag.domain.aggregate.InfoOrder;
import com.github.mrag.domain.aggregate.entity.InfoOrderItem;
import com.github.mrag.domain.service.InfoOrderService;
import com.github.mrag.domain.service.MoneyService;
import com.github.mrag.types.CurrencyCode;
import com.github.mrag.types.Item;
import com.github.mrag.types.Money;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InfoOrderServiceImpl implements InfoOrderService {

    private final MoneyService moneyService;

    public InfoOrderServiceImpl(MoneyService moneyService) {
        this.moneyService = moneyService;
    }

    @Override
    public void orderCombination(InfoOrder order, List<InfoOrderItem> items) {
        // 计算子单数量
        order.setItems(items);
        order.setSubOrderCount(items.size());

        // 计算总价
        List<Money> units = items
                .stream().map(InfoOrderItem::getItem)
                .map(Item::getItemPrice).collect(Collectors.toList());
        order.setTotalPrice(moneyService.calculateTotal(units, CurrencyCode.CNY));
    }
}
