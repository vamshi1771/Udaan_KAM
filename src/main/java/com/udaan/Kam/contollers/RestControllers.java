package com.udaan.Kam.contollers;

import com.udaan.Kam.dto.*;
import com.udaan.Kam.entity.Location;
import com.udaan.Kam.entity.POC;
import com.udaan.Kam.entity.RestaurantLead;
import com.udaan.Kam.services.CallPlaningService;
import com.udaan.Kam.services.PerformanceMetricsService;
import com.udaan.Kam.services.PocService;
import com.udaan.Kam.services.implementation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
public class RestControllers {

    @Autowired RestaurantServiceImpl restaurantService;
    @Autowired PocServiceImpl pocService;
    @Autowired
    InteractionServiceImpl interactionService;

    @Autowired
    CallPlaningServiceImpl callPlaningService;

    @Autowired
    PerformanceServiceImp performanceServiceImp;


    @PostMapping("/register-restaurant")
    void registerRestaurant(@RequestBody RestaurantDto restaurantDto){
        restaurantService.register(restaurantDto);
    }

    @PostMapping("/register-poc")
    void registerPoc(@RequestBody PocDto poc)
    {
        pocService.registerPoc(poc);
    }

    @PostMapping("/add-interaction")
    void addInteraction(@RequestBody InteractionDto interactionDto){
        interactionService.makeInteraction(interactionDto);
    }

    @GetMapping("/get-today-interactions")
    List<RestaurantsToInteract> getRestaurantsToInteract(){
        return callPlaningService.getCalls();
    }

    @GetMapping("/get-week-performing-leads")
    List<RestaurantLead> getWeekPerforming(){
        return restaurantService.getWeekPerforming();
    }

    @GetMapping("/get-performance-metrics/{year}")
    List<PerformanceDto> getPerformanceMetrics(@PathVariable Long year){
        return performanceServiceImp.getPerformance(year);
    }
}
