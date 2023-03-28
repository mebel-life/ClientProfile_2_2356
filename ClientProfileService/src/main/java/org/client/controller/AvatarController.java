package org.client.controller;

import lombok.AllArgsConstructor;
import org.client.common.dto.AvatarDto;
import org.client.service.AvatarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avatar")
@AllArgsConstructor
public class AvatarController {

    private final AvatarService avatarService;

    @GetMapping
    public ResponseEntity<AvatarDto> getById(@RequestParam String icp) {
        return new ResponseEntity<>(avatarService.getAvatarClient(icp), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Void> createAvatar(@RequestParam String icp) {
        avatarService.addAvatarForClient(icp);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }
    //получаю от АВАТАРА
    @PutMapping("/api")
    public ResponseEntity<Void> setAvatar(@RequestBody AvatarDto avatarDto) {
        avatarService.setAvatarForClient(avatarDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
