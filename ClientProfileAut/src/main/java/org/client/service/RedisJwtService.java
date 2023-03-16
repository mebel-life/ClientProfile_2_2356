package org.client.service;

import org.client.model.JwtResponse;
import org.client.repository.RedisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

@Service
public class RedisJwtService {

    private final RedisRepository repository;
    @Autowired
    public RedisJwtService(RedisRepository repository) {
        this.repository = repository;
    }

    //Saves token to Redis
    public void saveToken(JwtResponse response) {
        repository.save(response);
    }

    //Returns token for username and password from Redis if user is logged in
    public String checkForToken(String username, String password) {
        try {
            JwtResponse response = repository.findById(username).get();
            if (password.equals(response.getPassword())) {
                return response.getToken();
            }
        } catch (RuntimeException e) {
            throw new BadCredentialsException("Invalid credentials or user unauthorized");
        }
        return "";
    }
}
