package com.github.mrag.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class OrderItemSubmitDTO {

    @NotNull(message = "商品编码为空")
    private Integer itemId;
}
