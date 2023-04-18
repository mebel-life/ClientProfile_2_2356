package org.client.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.client.common.dto.AvatarDto;
import org.client.service.AvatarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 *
 */
@Slf4j
@RestController
@RequestMapping("/avatar")
@AllArgsConstructor
public class AvatarController {

    //    private final JmsTemplate jmsTemplate;
    private final AvatarService avatarService;

    //получаю ответ от сервиса на загрузку аватара, загрузил 6 картинок в ресурсы, передаю их в метод по формированию в AvatarDto

    @PostMapping
    public ResponseEntity<AvatarDto> uploadAvatar(@RequestBody String uuid, @RequestParam String icp) throws IOException {
        log.info("Uploading avatar, uuid = " + uuid);
        return new ResponseEntity<>(avatarService.uploadAvatar(uuid, icp), HttpStatus.OK);
    }





//    @PostMapping
//    public ResponseEntity<?> uploadAvatar(@RequestParam("avatar") MultipartFile file) throws IOException, JMSException {
//        logger.info("Uploading avatar");
//        MQQueue avatarMQQue = new com.ibm.mq.jakarta.jms.MQQueue("AVATAR.REQUEST");
//        jmsTemplate.convertAndSend((Destination) avatarMQQue,file);
//        String uploadAvatar = avatarService.uploadAvatar(file);
//        return ResponseEntity.status(HttpStatus.OK).body(uploadAvatar);
//    }
//
//    @GetMapping("/{uuid}")
//    public ResponseEntity<?> downloadAvatar(@PathVariable String uuid) throws IOException {
//        logger.info("Downloading avatar");
//        byte[] avatar;
//        try {
//            avatar = avatarService.getAvatar(uuid);
//        } catch (NoSuchElementException e) {
//            logger.warn(String.format("Avatar with %s uuid not found", uuid));
//            var imgFile = new ClassPathResource("image/error.jpg");
//            byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());
//
//            return ResponseEntity
//                    .ok()
//                    .contentType(MediaType.IMAGE_JPEG)
//                    .body(bytes);
//        }
//        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png"))
//                .body(avatar);
//    }

//    @GetMapping("/info/{uuid}")
//    public ResponseEntity<?> getImageInfoByUuid(@PathVariable("uuid") String uuid) {
//        logger.info("Getting info of avatar ");
//        AvatarDto avatarDto = null;
//        try {
//            avatarDto = avatarService.getInfoByUuid(uuid);
//
//        } catch (NoSuchElementException e) {
//            logger.warn(String.format("Avatar with %s uuid not found", uuid));
//        }
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(avatarDto);
//    }
}

