package com.github.mrag.types;

import lombok.Getter;

import java.util.Currency;

public enum CurrencyCode {
    CNY, USD, TWD, HKD, EUR, JPY;

    @Getter
    private final Currency currency;

    CurrencyCode() {
        currency = Currency.getInstance(name());
    }
}
