package org.client.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if("user".equals(username)){
            return new User("user","$2a$12$vIzzPu2kXDAGwgPMSY2isOsRESZjtcObq1qPXbvIdGhzn0omvXZm2",new ArrayList<>());
        }else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
