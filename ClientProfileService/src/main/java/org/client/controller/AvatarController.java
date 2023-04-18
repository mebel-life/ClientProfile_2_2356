package org.client.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.client.common.dto.AvatarDto;
import org.client.service.AvatarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avatar")
@AllArgsConstructor
@Slf4j
public class AvatarController {

    private final AvatarService avatarService;

    @GetMapping("/{icp}")
    public ResponseEntity<AvatarDto> getById(@PathVariable(value="icp") String icp) {
        log.info(icp);
        return new ResponseEntity<>(avatarService.getAvatarClient(icp), HttpStatus.OK);
    }

    @PutMapping //http://localhost:8080/avatar?icp=2340
    public ResponseEntity<AvatarDto> createAvatar(@RequestParam String icp) {
        return new ResponseEntity<>(avatarService.addAvatarForClient(icp), HttpStatus.OK);

    }
    //получаю от АВАТАРА
    @PutMapping("/api")
    public ResponseEntity<Void> setAvatar(@RequestBody AvatarDto avatarDto, @RequestParam String icp) {
        log.info("avatarDto пришел" + avatarDto.getAvatarName());
        avatarService.setAvatarForClient(avatarDto, icp);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
