package com.udaan.Kam.contollers;


import com.udaan.Kam.dto.AuthResponseDto;
import com.udaan.Kam.entity.Users;
import com.udaan.Kam.services.AuthenticationService;
import com.udaan.Kam.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;



    @PostMapping("/register")
    public AuthResponseDto register(@RequestBody Users user, HttpServletResponse response){
        return authenticationService.register(user,response);

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user, HttpServletResponse response){ return authenticationService.login(user,response);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        System.out.println("Logout successfully");
       return authenticationService.logout(request);
    }
}