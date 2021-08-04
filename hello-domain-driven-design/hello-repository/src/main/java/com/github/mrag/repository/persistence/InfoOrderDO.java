package com.github.mrag.repository.persistence;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InfoOrderDO implements Serializable {
    private Long orderId;

    private BigDecimal totalPrice;

    private String customerName;

    private Integer subOrderCount;

    private LocalDateTime createTime;

    private LocalDateTime finishTime;

    private static final long serialVersionUID = 1L;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getSubOrderCount() {
        return subOrderCount;
    }

    public void setSubOrderCount(Integer subOrderCount) {
        this.subOrderCount = subOrderCount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", customerName=").append(customerName);
        sb.append(", subOrderCount=").append(subOrderCount);
        sb.append(", createTime=").append(createTime);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}