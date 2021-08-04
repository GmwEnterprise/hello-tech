package com.github.mrag.domain.service;

import com.github.mrag.types.CurrencyCode;
import com.github.mrag.types.Money;

import java.util.List;

public interface MoneyService {

    Money calculateTotal(List<Money> units, CurrencyCode currencyCode);
}
