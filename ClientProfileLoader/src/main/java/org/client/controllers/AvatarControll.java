package org.client.controllers;
import lombok.AllArgsConstructor;
import org.client.common.dto.AvatarDto;
import org.client.service.AvatarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/avatar")
@AllArgsConstructor
public class AvatarControll {

    private final AvatarService avatarService;

    @GetMapping //http://localhost:8085/api/avatar?icp=2341
    public ResponseEntity<AvatarDto> getAvatar(@RequestParam String icp) {
          return new ResponseEntity<>(avatarService.getAvatar(icp), HttpStatus.OK);
    }
    @PutMapping
    public  ResponseEntity<AvatarDto> setAvatar(@RequestBody AvatarDto avatarDto, @RequestParam String icp) {
        avatarService.setAvatar(avatarDto, icp);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
