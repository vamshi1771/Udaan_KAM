package com.udaan.Kam.dto;

import lombok.Data;

@Data
public class AverageOrdersPerMonth {
    private String restaurantName;
    private Long  restaurantId;
    private Long totalMonths;
    private Long totalOrderCount;
    private String restaurantType;
    public AverageOrdersPerMonth(String restaurantName, Long restaurantId, Long totalMonths, Long totalOrderCount, String restaurantType) {
        this.restaurantName = restaurantName;
        this.restaurantId = restaurantId;
        this.totalMonths = totalMonths;
        this.totalOrderCount = totalOrderCount;
        this.restaurantType = restaurantType;
    }
}
