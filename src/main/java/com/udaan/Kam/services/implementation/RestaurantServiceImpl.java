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
                .setRestaurantId(restaurantDto.getRestaurantId())
                .setRestaurantName(restaurantDto.getRestaurantName())
                .setLocation(restaurantDto.getLocation())
                .setRestaurantType(restaurantDto.getRestaurantType())
                .setLeadStatus("NEW")
                .build();
        restaurantRepository.save(restaurant);
        CallPlaning callPlaning = new CallPlaning();
        callPlaning.setCall_frequency(restaurantDto.getCallFrequency());
        callPlaning.setRestaurantId(restaurantDto.getRestaurantId());
        callPlaningRepository.save(callPlaning);
    }

    @Override
    public List<RestaurantLead> getWeekPerforming() {
        return null;
    }

//    public List<RestaurantLead> getWeekPerforming(){
////        restaurantRepository
//    }
}
