package com.udaan.Kam.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class RestaurantsToInteract {
    private  Long restaurantId;
    private LocalDate lastInteractedDate;
    private  List<PocDto> restaurantPocs;

    public RestaurantsToInteract() {
        this.restaurantId = restaurantId;
        this.lastInteractedDate = lastInteractedDate;
        this.restaurantPocs = restaurantPocs;
    }

}
