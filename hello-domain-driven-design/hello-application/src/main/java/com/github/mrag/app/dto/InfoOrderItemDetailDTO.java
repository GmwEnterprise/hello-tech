package com.github.mrag.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class InfoOrderItemDetailDTO {

    private String orderItemId;
    private BigDecimal price;
    private String itemId;
    private String itemName;
}
