package com.udaan.Kam.services.implementation;

import com.udaan.Kam.dto.RestaurantLastCallDate;
import com.udaan.Kam.dto.RestaurantsToInteract;
import com.udaan.Kam.entity.POC;
import com.udaan.Kam.repository.CallPlaningRepository;
import com.udaan.Kam.repository.PocRepository;
import com.udaan.Kam.services.CallPlaningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

public class CallPlaningServiceImpl implements CallPlaningService {

    @Autowired
    CallPlaningRepository callPlaningRepository;
    @Autowired
    PocRepository pocRepository;
    @Override
    @Transactional
    public List<RestaurantsToInteract> getCalls() {
     List<RestaurantLastCallDate> restaurantsToCall = callPlaningRepository.getCalls();
     List<POC> pocList = pocRepository.findAll();
        return getRestaurantsToInteract(restaurantsToCall,pocList);

    }
    public List<RestaurantsToInteract> getRestaurantsToInteract(
            List<RestaurantLastCallDate> restaurantsToCall,
            List<POC> pocList) {

        return restaurantsToCall.stream()
                .map(restaurant -> {
                    List<POC> filteredPocs = pocList.stream()
                            .filter(poc -> poc.getRestaurantId().equals(restaurant.getRestaurantId()))
                            .collect(Collectors.toList());

                    RestaurantsToInteract interaction = new RestaurantsToInteract();
                    interaction.setRestaurantId(restaurant.getRestaurantId());
                    interaction.setLastInteractedDate(restaurant.getLastCallDate());
                    interaction.setRestaurantPocs(filteredPocs);

                    return interaction;
                })
                .collect(Collectors.toList());
    }
}
