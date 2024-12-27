package com.udaan.Kam.services;

import com.udaan.Kam.entity.Users;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    public Users register(Users user);
    public ResponseEntity<?> login(Users user)throws IllegalArgumentException;
}
