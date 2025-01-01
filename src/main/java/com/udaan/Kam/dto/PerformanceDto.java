package com.udaan.Kam.dto;

import lombok.Data;

import java.util.List;

@Data
public class PerformanceDto {
    private Long RestaurantId;
    private String RestaurantName;
    List<MonthOrderCount> monthOrderCounts;
}
