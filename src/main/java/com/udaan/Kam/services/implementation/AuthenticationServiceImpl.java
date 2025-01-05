package com.udaan.Kam.services.implementation;

import com.udaan.Kam.dto.AuthResponseDto;
import com.udaan.Kam.entity.Users;
import com.udaan.Kam.repository.UserRepository;
import com.udaan.Kam.services.AuthenticationService;
import com.udaan.Kam.services.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenBlacklistService tokenBlacklistService;

    @Override
    public AuthResponseDto register(Users user, HttpServletResponse response) {
        user.setPassword(encoder.encode(user.getPassword()));
       Users registeredUser = userRepository.save(user);
        String token =   jwtService.generateToken(user.getUserName());
        return new AuthResponseDto(
                token,
                null,
                registeredUser.getUserName(),
                registeredUser.getRole(),
                registeredUser.getUser_id(),
                registeredUser.getEmail()
        );
    }

    @Override
    public ResponseEntity<?> login(Users user, HttpServletResponse response) throws IllegalArgumentException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            Users loggedUser = userRepository.findByUserName(user.getUserName());
            if(authentication.isAuthenticated()){
                String token =   jwtService.generateToken(user.getUserName());
                System.out.println(loggedUser.getUser_id());
                AuthResponseDto authResponseDto = new AuthResponseDto(
                        token,
                        null,
                        loggedUser.getUserName(),
                        loggedUser.getRole(),
                        loggedUser.getUser_id(),
                        loggedUser.getEmail()
                );
                return ResponseEntity.ok(authResponseDto);
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authentication failed: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authentication failed: Invalid username or password.");
    }

    @Override
    public String logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer")) {
            token = authHeader.substring(7);
        }

        if(token != null) {
            tokenBlacklistService.blacklist(token);
            return "Logout successful";
        } else {
            return "No active session found";
        }
    }

}
