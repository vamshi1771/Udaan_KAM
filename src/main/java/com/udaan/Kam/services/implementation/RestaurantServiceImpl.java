package com.udaan.Kam.services.implementation;

import com.udaan.Kam.dto.RestaurantDto;
import com.udaan.Kam.dto.RestaurantsToInteract;
import com.udaan.Kam.entity.CallPlaning;
import com.udaan.Kam.entity.Location;
import com.udaan.Kam.entity.RestaurantLead;
import com.udaan.Kam.repository.CallPlaningRepository;
import com.udaan.Kam.repository.InteractionsRepository;
import com.udaan.Kam.repository.RestaurantRepository;
import com.udaan.Kam.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    InteractionsRepository interactionsRepository;
    @Autowired
    CallPlaningRepository callPlaningRepository;

    @Override
    @Transactional
    public void register(RestaurantDto restaurantDto) {
        RestaurantLead restaurant = new RestaurantLead.Builder()
                .setRestaurantName(restaurantDto.getRestaurantName())
                .setLocation(restaurantDto.getLocation())
                .setRestaurantType(restaurantDto.getRestaurantType())
                .setLeadStatus("NEW")
                .build();
        RestaurantLead savedRestaurant = restaurantRepository.save(restaurant);

        // Create and save the CallPlaning entity
        CallPlaning callPlaning = new CallPlaning();
        callPlaning.setCallFrequency(restaurantDto.getCallFrequency());
        callPlaning.setRestaurantLead(savedRestaurant); // Set the relationship
        callPlaningRepository.save(callPlaning);
    }

    @Override
    public List<RestaurantLead> getWeekPerforming() {
        return null;
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        List<RestaurantLead> restaurantLeads = restaurantRepository.findAll();
        return restaurantLeads.stream()
                .map(lead -> new RestaurantDto(
                        lead.getRestaurantId(),
                        lead.getRestaurantName(),
                        lead.getLocation(),
                        lead.getRestaurantType(),
                        lead.getLeadStatus(),
                        null
                ))
                .collect(Collectors.toList());

    }
}
