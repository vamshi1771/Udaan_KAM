package com.udaan.Kam.contollers;


import com.udaan.Kam.entity.Users;
import com.udaan.Kam.services.AuthenticationService;
import com.udaan.Kam.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;



    @PostMapping("/register")
    public Users register(@RequestBody Users user,HttpServletResponse response){
        Users user1 = authenticationService.register(user,response);
        return user1;
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user, HttpServletResponse response){ return authenticationService.login(user,response);
    }
}