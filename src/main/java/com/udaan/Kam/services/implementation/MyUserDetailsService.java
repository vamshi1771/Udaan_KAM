package com.udaan.Kam.services.implementation;

import com.udaan.Kam.entity.UserPrincipal;
import com.udaan.Kam.entity.Users;
import com.udaan.Kam.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user1 = userRepository.findByUserName(username);
        if(user1 == null){
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserPrincipal(user1);
    }
}
