package com.github.mrag.domain.service.impl;

import com.github.mrag.domain.service.MoneyService;
import com.github.mrag.types.CurrencyCode;
import com.github.mrag.types.Money;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MoneyServiceImpl implements MoneyService {
    @Override
    public Money calculateTotal(List<Money> units, CurrencyCode currencyCode) {
        // todo
        BigDecimal total = units.stream().map(Money::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new Money(total.toString(), currencyCode);
    }
}
