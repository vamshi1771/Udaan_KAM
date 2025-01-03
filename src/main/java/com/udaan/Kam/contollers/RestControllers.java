package com.udaan.Kam.contollers;

import com.udaan.Kam.dto.*;
import com.udaan.Kam.entity.Location;
import com.udaan.Kam.entity.POC;
import com.udaan.Kam.entity.RestaurantLead;
import com.udaan.Kam.repository.OrderRepository;
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

    @Autowired
    OrderServiceImpl orderService;

    @PostMapping("/register-restaurant") // to register a restaurant
    void registerRestaurant(@RequestBody RestaurantDto restaurantDto){
        restaurantService.register(restaurantDto);
    }

    @GetMapping("/get-restaurants")
    List<RestaurantDto> getAllRestaurants(){
       return  restaurantService.getAllRestaurants();
    }

    @PostMapping("/register-poc") // to register a Point of contact for restaurant
    void registerPoc(@RequestBody PocDto poc)
    {
        pocService.registerPoc(poc);
    }

    @PostMapping("/add-interaction") // to add interaction happened with restaurant
    void addInteraction(@RequestBody InteractionDto interactionDto){
        interactionService.makeInteraction(interactionDto);
    }

    @GetMapping("/get-today-interactions") // to get all Interaction to be made for today's
    List<RestaurantsToInteract> getRestaurantsToInteract(){
        return callPlaningService.getCalls();
    }

    @GetMapping("/get-week-performing-leads")
    List<RestaurantLead> getWeekPerforming(){
        return restaurantService.getWeekPerforming();
    }

    @GetMapping("/get-performance-metrics/{year}") // to get total orders made by each restaurant in each month of specified year
    List<PerformanceDto> getPerformanceMetrics(@PathVariable Long year){
        return performanceServiceImp.getPerformance(year);
    }

    @GetMapping("/average-orders-month") // to get total orders in month and restaurant types

    List<AverageOrdersPerMonth> getAverageOrder(){
        return orderService.getAverageOrders();
    }

}
