package com.udaan.Kam.dto;

import lombok.Data;

@Data
public class MonthOrderCount {
    private Integer month;
    private Long OrderCount;
    private Long averageOrderAmount;

    public MonthOrderCount(Integer month, Long totalOrders, Long averageOrderAmount) {
        this.month = month;
        this.OrderCount = totalOrders;
        this.averageOrderAmount = averageOrderAmount;
    }
}
