package com.udaan.Kam.dto;

import com.udaan.Kam.entity.POC;
import com.udaan.Kam.services.PocService;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class RestaurantsToInteract {
    private  Long restaurantId;
    private LocalDate lastInteractedDate;
    private  List<POC> restaurantPocs;

}
