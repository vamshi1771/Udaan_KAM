package com.udaan.Kam.services.implementation;

import com.udaan.Kam.dto.PocDto;
import com.udaan.Kam.dto.RestaurantLastCallDate;
import com.udaan.Kam.dto.RestaurantsToInteract;
import com.udaan.Kam.entity.POC;
import com.udaan.Kam.repository.CallPlaningRepository;
import com.udaan.Kam.repository.PocRepository;
import com.udaan.Kam.services.CallPlaningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CallPlaningServiceImpl implements CallPlaningService {

    @Autowired
    CallPlaningRepository callPlaningRepository;
    @Autowired
    PocRepository pocRepository;
    @Override
    @Transactional
    public List<RestaurantsToInteract> getCalls() {
        List<Object[]> calls = callPlaningRepository.getCalls();
        System.out.println("calls => \n"+calls);
        List<RestaurantLastCallDate> restaurantsToCall = mapCalls(calls);
        System.out.println("restaurantsToCall => \n"+restaurantsToCall);
        List<POC> pocList = pocRepository.findAll();
        List<PocDto> pocDtoList = pocList.stream().map(
                poc -> new PocDto.Builder()
                        .setPocId(poc.getPocId())
                        .setPocName(poc.getPocName())
                        .setRole(poc.getRole())
                        .setContactNumber(poc.getContactNumber())
                        .setEmailAddress(poc.getEmailAddress())
                        .setRestaurantId(poc.getRestaurantLead().getRestaurantId()) // Assuming POC has a getRestaurantLead() method
                        .build()
        ).collect(Collectors.toList());
        return getRestaurantsToInteract(restaurantsToCall,pocDtoList);

    }

    public List<RestaurantLastCallDate> mapCalls(List<Object[]> result) {
        return result.stream().map(record -> {
            Long restaurantId = ((BigInteger) record[0]).longValue(); // assuming the first field is restaurant_id
            java.sql.Date sqlDate = (java.sql.Date) record[1]; // Assuming the second field is last_call_date
            LocalDate lastCallDate = sqlDate.toLocalDate();  // assuming the second field is last_call_date

            RestaurantLastCallDate restaurant = new RestaurantLastCallDate();
            restaurant.setRestaurantId(restaurantId);
            restaurant.setLastCallDate(lastCallDate);

            return restaurant;
        }).collect(Collectors.toList());
    }
    public List<RestaurantsToInteract> getRestaurantsToInteract(
            List<RestaurantLastCallDate> restaurantsToCall,
            List<PocDto> pocList) {

        return restaurantsToCall.stream()
                .map(restaurant -> {
                    // Filter the list of PocDto based on restaurantId
                    List<PocDto> filteredPocs = pocList.stream()
                            .filter(poc -> poc.getRestaurantId().equals(restaurant.getRestaurantId()))
                            .collect(Collectors.toList());

                    // Create and populate RestaurantsToInteract object
                    RestaurantsToInteract interaction = new RestaurantsToInteract();
                    interaction.setRestaurantId(restaurant.getRestaurantId());
                    interaction.setLastInteractedDate(restaurant.getLastCallDate());
                    interaction.setRestaurantPocs(filteredPocs); // Assuming it can accept a List<PocDto>

                    return interaction;
                })
                .collect(Collectors.toList());
    }
}
