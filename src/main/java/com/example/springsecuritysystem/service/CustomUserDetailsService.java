package com.example.springsecuritysystem.service;

import com.example.springsecuritysystem.entity.system.user.Users;
import com.example.springsecuritysystem.service.implementation.UsersServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

/**
 * @author Touhid Hossain
 */
@Service(value = "userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersService usersService;

    public CustomUserDetailsService(UsersServiceImpl usersService) {
        this.usersService = usersService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Users> optionalUser = usersService.getUserByUserName(s);
        if (!optionalUser.isPresent()) optionalUser = usersService.getUserByEmail(s);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(optionalUser.get());
    }
}
