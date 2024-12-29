package com.udaan.Kam.services;


import com.udaan.Kam.dto.RestaurantDto;
import org.springframework.stereotype.Service;


public interface RestaurantService {

    void register(RestaurantDto restaurantDto);
}
