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

    public Money(String amount) {
        this.amount = new BigDecimal(amount);
        this.currency = Currency.getInstance(Locale.CHINA);
    }

    public Money(String amount, Locale locale) {
        this.amount = new BigDecimal(amount);
        this.currency = Currency.getInstance(locale);
    }

    public Money(String amount, CurrencyCode code) {
        this.amount = new BigDecimal(amount);
        this.currency = code.getCurrency();
    }

    @Override
    public BigDecimal getValue() {
        return amount;
    }
}
