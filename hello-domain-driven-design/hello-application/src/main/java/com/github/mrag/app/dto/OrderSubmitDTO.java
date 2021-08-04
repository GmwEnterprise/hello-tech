package com.github.mrag.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
@ToString
public class OrderSubmitDTO {

    @NotBlank(message = "客户姓名不能为空")
    private String customer;

    @Valid
    @NotEmpty(message = "子单不能为空")
    private List<OrderItemSubmitDTO> items;
}
