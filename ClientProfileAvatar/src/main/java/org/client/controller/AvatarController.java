package org.client.controller;

import lombok.RequiredArgsConstructor;
import org.client.entity.AvatarDto;
import org.client.service.AvatarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
<<<<<<< HEAD
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
=======
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
>>>>>>> 9d6c5603479e78aab97212b5d2d6b6ef272c580b
import java.util.NoSuchElementException;


/**
 *
 */
@RestController
@RequestMapping("/avatar")
@RequiredArgsConstructor
public class AvatarController {

    Logger logger = LoggerFactory.getLogger(AvatarController.class);
    private final AvatarService avatarService;

    @PostMapping
    public ResponseEntity<?> uploadAvatar(@RequestParam("avatar") MultipartFile file) throws IOException {
        logger.info("Uploading avatar");
        String uploadAvatar = avatarService.uploadAvatar(file);
        return ResponseEntity.status(HttpStatus.OK).body(uploadAvatar);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> downloadAvatar(@PathVariable String uuid) throws IOException {
        logger.info("Downloading avatar");
        byte [] avatar;
        try{
            avatar = avatarService.getAvatar(uuid);
        } catch (NoSuchElementException e) {
            logger.warn(String.format("Avatar with %s uuid not found", uuid));
            var imgFile = new ClassPathResource("image/error.jpg");
            byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png"))
                .body(avatar);
    }

    @GetMapping("/info/{uuid}")
    public ResponseEntity<?> getImageInfoByUuid(@PathVariable("uuid") String uuid) {
        logger.info("Getting info of avatar ");
        AvatarDto avatarDto = null;
        try {
            avatarDto = avatarService.getInfoByUuid(uuid);

        } catch (NoSuchElementException e) {
            logger.warn(String.format("Avatar with %s uuid not found", uuid));
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(avatarDto);
    }
}
