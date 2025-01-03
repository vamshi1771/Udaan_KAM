package com.udaan.Kam.services;

import com.udaan.Kam.entity.Users;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    public Users register(Users user,HttpServletResponse response);
    public ResponseEntity<?> login(Users user, HttpServletResponse response)throws IllegalArgumentException;
}
