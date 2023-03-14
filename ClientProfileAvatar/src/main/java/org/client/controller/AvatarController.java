package org.client.controller;

import com.ibm.mq.MQException;
import com.ibm.mq.jakarta.jms.MQQueue;
import lombok.RequiredArgsConstructor;
import org.client.entity.AvatarDto;
import org.client.service.AvatarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.Destination;
import javax.jms.JMSException;
import java.io.IOException;
import java.util.NoSuchElementException;


/**
 *
 */
@RestController
@RequestMapping("/avatar")
@RequiredArgsConstructor
public class AvatarController {
    Logger logger = LoggerFactory.getLogger(AvatarController.class);

//    private final JmsTemplate jmsTemplate;
    private final AvatarService avatarService;

    //получаю ответ от сервиса на загрузку аватара, загрузил пять картинок в ресурсы, пытась их передать в метод по формированию в AvatarDto
    //метод getRandomImage() - выдает случайное число от 1 до 5 соответсвуещее названию файла Аватара


    //не совсем понимаю как контроллер и сервис для него реализовать

    @PostMapping
    public ResponseEntity<?> uploadAvatar(@PathVariable String uuid) throws IOException {
        logger.info("Uploading avatar");
        String uploadAvatar = avatarService.uploadAvatar((MultipartFile) new ClassPathResource("image/" + avatarService.getRandomImage() + ".jpg"));
        return ResponseEntity.status(HttpStatus.OK).body(uploadAvatar);
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
