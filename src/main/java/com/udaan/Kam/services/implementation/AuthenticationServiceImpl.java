package com.udaan.Kam.services.implementation;

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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
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

    @Override
    public Users register(Users user,HttpServletResponse response) {
        user.setPassword(encoder.encode(user.getPassword()));
       Users registeredUser = userRepository.save(user);
       if(registeredUser != null){
           String token =   jwtService.generateToken(user.getUserName());
           Cookie cookie = new Cookie("jwt_token", token);
//           cookie.setHttpOnly(true);
//           cookie.setSecure(true);
           cookie.setPath("/");
           cookie.setMaxAge(24 * 60 * 60);
           response.addCookie(cookie);
       }
        return registeredUser;
    }

    @Override
    public ResponseEntity<?> login(Users user, HttpServletResponse response) throws IllegalArgumentException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            if (authentication.isAuthenticated()) {
               String token =   jwtService.generateToken(user.getUserName());
                Users loggedUser = userRepository.findByUserName(user.getUserName());

                Cookie cookie = new Cookie("jwt_token", token);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setPath("/");
                cookie.setMaxAge(72 * 60 * 60);
                response.addCookie(cookie);

                return ResponseEntity.ok(loggedUser);
            }
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Authentication failed: " + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Authentication failed: Invalid username or password.");
    }

}
