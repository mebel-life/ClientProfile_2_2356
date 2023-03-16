package org.client.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private static final String PASSWORD = "$2a$10$BVxGQ5emcqwGl3bKUrFni.Ix8pCYig/LdvWMh2i5LN/1X/9Hzvsfa"; // 200
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return switch (username) {
            case "user" -> new User("user", "user", List.of((GrantedAuthority) () -> "USER"));
            case "admin" -> new User("admin", "admin", List.of((GrantedAuthority) () -> "ADMIN"));
            default -> throw new UsernameNotFoundException("User not found with username: " + username);
        };
    }
}
