package com.udaan.Kam.contollers;

import com.udaan.Kam.dto.RestaurantDto;
import com.udaan.Kam.entity.POC;
import com.udaan.Kam.services.PocService;
import com.udaan.Kam.services.implementation.PocServiceImpl;
import com.udaan.Kam.services.implementation.RestaurantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class RestControllers {

    @Autowired RestaurantServiceImpl restaurantService;
    @Autowired PocServiceImpl pocService;

    @PostMapping("/register-restaurant")
    void registerRestaurant(@RequestBody RestaurantDto restaurantDto){
        restaurantService.register(restaurantDto);
    }

    @PostMapping("/register-poc")
    void registerPoc(@RequestBody POC poc)
    {
        pocService.registerPoc(poc);
    }

}
