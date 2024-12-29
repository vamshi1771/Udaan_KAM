package com.udaan.Kam.services.implementation;

import com.udaan.Kam.dto.RestaurantDto;
import com.udaan.Kam.entity.Location;
import com.udaan.Kam.entity.RestaurantLead;
import com.udaan.Kam.repository.RestaurantRepository;
import com.udaan.Kam.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Override
    public void register(RestaurantDto restaurantDto) {
        RestaurantLead restaurant = new RestaurantLead.Builder()
                .setRestaurantId(restaurantDto.getRestaurantId())
                .setRestaurantName(restaurantDto.getRestaurantName())
                .setLocation(restaurantDto.getLocation())
                .setRestaurantType(restaurantDto.getRestaurantType())
                .setLeadStatus("NEW")
                .build();
        restaurantRepository.save(restaurant);
    }
}
