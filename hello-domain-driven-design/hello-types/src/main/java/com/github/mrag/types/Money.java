package com.github.mrag.types;

import com.github.mrag.common.DomainPrimitive;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

@Value
@EqualsAndHashCode(callSuper = true)
public class Money extends DomainPrimitive<BigDecimal> {

    // 金额
    BigDecimal amount;

    // 货币
    Currency currency;

    public Money(BigDecimal amount) {
        this.amount = amount;
        this.currency = Currency.getInstance(Locale.CHINA);
    }

    public Money(BigDecimal amount, Locale locale) {
        this.amount = amount;
        this.currency = Currency.getInstance(locale);
    }

    public Money(BigDecimal amount, String code) {
        this.amount = amount;
        // CNY, USD, TWD, HDK, EUR, JPY
        this.currency = Currency.getInstance(code);
    }

    @Override
    public BigDecimal getValue() {
        return amount;
    }
}
