package com.udaan.Kam.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
public class RestaurantsToInteract {
    private  Long restaurantId;
    private LocalDate lastInteractedDate;
    private  String restaurantName;
    private  Long CallFrequency;
    private  List<PocDto> restaurantPocs;

//    public RestaurantsToInteract(Long restaurantId,String restaurantName, String lastInteractedTime,LocalDate lastInteractedDate, List<PocDto> restaurantPocs) {
//        this.restaurantId = restaurantId;
//        this.CallFrequency = ;
//        this.lastInteractedDate = lastInteractedDate;
//        this.restaurantPocs = restaurantPocs;
//        this.lastInteractedTime = lastInteractedTime;
//        this.restaurantName = restaurantName;
//    }
}
