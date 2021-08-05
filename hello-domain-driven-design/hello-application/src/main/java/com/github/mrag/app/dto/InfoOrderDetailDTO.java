package com.github.mrag.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

@Getter
@Setter
@ToString
public class InfoOrderDetailDTO {

    private String orderId;
    private BigDecimal totalPrice;
    private Currency currency;
    private String customerName;
    private Integer subOrderCount;
    private LocalDateTime createTime;
    private LocalDateTime finishTime;
    private List<InfoOrderItemDetailDTO> items;
}
