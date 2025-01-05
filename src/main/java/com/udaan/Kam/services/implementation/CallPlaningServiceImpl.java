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
import java.sql.Date;
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
        List<PocDto> pocDtoList = pocList.stream()
                .map(poc -> PocDto.builder()
                        .pocId(poc.getPocId())
                        .pocName(poc.getPocName())
                        .role(poc.getRole())
                        .contactNumber(poc.getContactNumber())
                        .emailAddress(poc.getEmailAddress())
                        .restaurantId(poc.getRestaurantLead().getRestaurantId()) // Assuming POC has a getRestaurantLead() method
                        .build()
                ).collect(Collectors.toList());
        return getRestaurantsToInteract(restaurantsToCall,pocDtoList);

    }

    public List<RestaurantLastCallDate> mapCalls(List<Object[]> result) {
        return result.stream().map(record -> {
            Long restaurantId = ((BigInteger) record[0]).longValue();
            LocalDate lastCallDate = null;
            if(record[1] != null) {
                Date sqlDate = (Date) record[1];
               lastCallDate = sqlDate.toLocalDate();
            }
            RestaurantLastCallDate restaurant = new RestaurantLastCallDate();
            restaurant.setRestaurantId(restaurantId);
            restaurant.setLastCallDate(lastCallDate);
            System.out.println("restaurantName => \n"+ record[2].toString());
            System.out.println("callFrequency => \n"+ ((BigInteger) record[3]).longValue());
                restaurant.setRestaurantName(record[2].toString());
                restaurant.setCallFrequency(((BigInteger) record[3]).longValue());
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
                    return RestaurantsToInteract.builder()
                            .restaurantId(restaurant.getRestaurantId())
                            .lastInteractedDate(restaurant.getLastCallDate())
                            .CallFrequency(restaurant.getCallFrequency())
                            .restaurantName(restaurant.getRestaurantName())
                            .restaurantPocs(filteredPocs)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
