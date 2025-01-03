package com.udaan.Kam.dto;

import com.udaan.Kam.entity.Location;
import com.udaan.Kam.entity.RestaurantLead;
import lombok.Data;

import javax.persistence.*;

@Data
public class RestaurantDto {
    private Long restaurantId;
    private String restaurantName;
    private String location;
    private String restaurantType;
    private String leadStatus;
    private Long callFrequency;

    public RestaurantDto(Long restaurantId, String restaurantName, String location, String restaurantType, String leadStatus, Object o) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.location = location;
        this.restaurantType = restaurantType;
        this.leadStatus = leadStatus;
    }
}
