package com.udaan.Kam.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RestaurantLastCallDate {
    private Long restaurantId;
    private LocalDate lastCallDate;
}
