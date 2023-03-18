package org.client.controller;

import org.client.common.dto.AvatarDto;
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

    @PutMapping
    public ResponseEntity<?> createAvatar(@RequestParam String icp) {

        avatarService.addAvatarForClient(icp);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    //получаю от АВАТАРА
    @PutMapping("/api")
    public ResponseEntity<?> setAvatar(@RequestBody AvatarDto avatarDto) {
        avatarService.setAvatarForClient(avatarDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
