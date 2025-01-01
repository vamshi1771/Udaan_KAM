package com.udaan.Kam.services.implementation;

import com.udaan.Kam.dto.PocDto;
import com.udaan.Kam.entity.POC;
import com.udaan.Kam.entity.RestaurantLead;
import com.udaan.Kam.repository.PocRepository;
import com.udaan.Kam.repository.RestaurantRepository;
import com.udaan.Kam.services.PocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PocServiceImpl implements PocService {

    @Autowired PocRepository pocRepository;

    @Autowired
    RestaurantRepository restaurantRepository;
    @Override
    public void registerPoc(PocDto poc) {
        Optional<RestaurantLead> restaurantLead = restaurantRepository.findById(poc.getRestaurantId());
        System.out.println(restaurantLead.isEmpty());
       if(restaurantLead.isPresent()) {
           RestaurantLead restaurantLead1 = restaurantLead.get();
//           System.out.println(restaurantLead1.getRestaurantId());
           POC poc1 = new POC.Builder()
                   .pocName(poc.getPocName())
                   .role(poc.getRole())
                   .contactNumber(poc.getContactNumber())
                   .emailAddress(poc.getEmailAddress())
                   .restaurantLead(restaurantLead1)
                   .build();
           pocRepository.save(poc1);
       }
    }
}
