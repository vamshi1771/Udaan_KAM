package com.udaan.Kam.dto;

import com.udaan.Kam.entity.RestaurantLead;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
public class PocDto {
    private Long pocId;
    private String pocName;
    private String role;
    private String contactNumber;
    private String emailAddress;
    private Long restaurantId;
}