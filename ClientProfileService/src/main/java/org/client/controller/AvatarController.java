package org.client.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.client.service.AvatarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam String icp) {
        return new ResponseEntity<>(avatarService.getAvatarClient(icp), HttpStatus.OK);
    }

    @PostMapping
    public void createAvatar(@Parameter String icp) {
        avatarService.addAvatarForClient(icp);
    }
}
