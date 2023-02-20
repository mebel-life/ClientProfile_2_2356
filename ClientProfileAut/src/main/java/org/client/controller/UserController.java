package org.client.controller;

import org.client.security.JWTutil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
public class UserController {

    private final JWTutil jwTutil;

    public UserController(JWTutil jwTutil) {
        this.jwTutil = jwTutil;
    }


    @GetMapping("/admin")
        public String adminEndpoint() {
            return "Admin!";
        }

        @GetMapping("/user")
        public String userEndpoint() {
            return "User!";
        }

        @GetMapping("/show")
        public String allRolesEndpoint(Principal principal) {
            return principal.toString();
        }

    }


