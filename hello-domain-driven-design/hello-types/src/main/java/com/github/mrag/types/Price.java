package com.github.mrag.types;

import com.github.mrag.common.DomainPrimitive;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.math.BigDecimal;

@Value
@EqualsAndHashCode(callSuper = true)
public class Price extends DomainPrimitive<BigDecimal> {

    BigDecimal amount;

    @Override
    public BigDecimal getValue() {
        return amount;
    }
}
