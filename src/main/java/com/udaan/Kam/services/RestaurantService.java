package com.udaan.Kam.services;


import com.udaan.Kam.dto.RestaurantDto;
import com.udaan.Kam.dto.RestaurantsToInteract;
import com.udaan.Kam.entity.RestaurantLead;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RestaurantService {

    void register(RestaurantDto restaurantDto);
    public List<RestaurantLead> getWeekPerforming();

    List<RestaurantDto> getAllRestaurants();
}
