package org.client.controller;

import io.jsonwebtoken.Jwt;
import org.client.model.JwtRequest;
import org.client.model.JwtResponse;
import org.client.service.JwtUserDetailsService;
import org.client.service.RedisJwtService;
import org.client.token.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private Logger logger = Logger.getLogger("Authentication");
    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;
    private JwtUserDetailsService userDetailsService;
    private RedisJwtService redisJwtService;
    @Autowired
    public JwtAuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService, RedisJwtService redisJwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.redisJwtService = redisJwtService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public JwtResponse createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        logger.info("Trying to authenticate user");
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        JwtResponse jwtResponse = new JwtResponse(token, authenticationRequest.getUsername(), authenticationRequest.getPassword(),10L);
        redisJwtService.saveToken(jwtResponse);
        logger.info("JWT token created");
        return jwtResponse;
    }


//    private ResponseEntity<?> checkAuth(String token, HttpServletRequest) {
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        userDetailsService.loadUserByUsername(username).getAuthorities().
//                forEach(grantedAuthority -> {
//                    if (grantedAuthority.getAuthority().equals("ADMIN")) {
//                        httpHeaders.setAccessControlAllowMethods(List.of(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE));
//                    }
//                    if (grantedAuthority.getAuthority().equals("USER")) {
//                        httpHeaders.setAccessControlAllowMethods(List.of(HttpMethod.GET));
//
//                    }
//                });
//        return ResponseEntity.ok().headers(httpHeaders).body(null);
//    }


    @RequestMapping(value = "/checkAuth")
    public ResponseEntity<?> checkAuth(HttpServletRequest request) {
        String token = redisJwtService.checkForToken(request.getHeader("username"), request.getHeader("password"));
        if (token == null) {
            logger.warning("No authenticated user found");
            throw new BadCredentialsException("No authenticated user found");
        }
        String username = jwtTokenUtil.getUsernameFromToken(token);
        Collection<? extends GrantedAuthority> authorities = userDetailsService.loadUserByUsername(username).getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ADMIN")) {
                logger.info("User granted access as admin");
                return ResponseEntity.status(200).build();
            } else if (authority.getAuthority().equals("USER") && request.getMethod().equals("GET")) {
                logger.info("User granted access as user");
                return ResponseEntity.status(200).build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


}
