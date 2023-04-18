package org.client.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import org.client.common.dto.AvatarDto;


import org.client.util.ImageData;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class AvatarServiceImp implements AvatarService {

    private final RestTemplate restTemplate = new RestTemplate();
//    private final AvatarRepo avatarRepo;

    private static final String URL_SERVICE = "http://localhost:8080/avatar/api?icp={icp}";


    @Transactional
    public AvatarDto uploadAvatar(String uuid, String icp) throws IOException {
        //метод getRandomImage() - выдает случайное число от 0 до 5 соответсвуещее названию файла Аватара
        String path = new StringBuilder().append("ClientProfileAvatar/src/main/resources/image/").append(getRandomImage()).append(".jpg").toString();

        File file = new File(path);
        log.info("File создан " + path);
        log.info("icp ={}", icp);

        // Передал в конструктор imageData в конвертацию byte[]
        ImageData imageData = new ImageData(file);

        // создал AvatarDTO
        AvatarDto avatarDto = AvatarDto.builder()
                .uuid(UUID.randomUUID().toString())
                .individualUuid(uuid)
                .avatarName(file.getName())
                .md5(DigestUtils.md5Hex(file.getName()))
                .fileSize(file.length())
                .byteSize(imageData.getImageData()).build();
        log.info(avatarDto.toString());

        // отправляю обратно в мкс Сервис
//         (avatarDto == null) ? sendToService(avatarDto) : new Exception();
        if (avatarDto != null) {
            sendToService(avatarDto, icp);
            return avatarDto;
        } else {
            log.info("avatarDto = null");
        }
        return null;
    }

    protected void sendToService (AvatarDto avatarDto, String icp) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<AvatarDto> entity = new HttpEntity<>(avatarDto, headers);
        ResponseEntity<AvatarDto> responseEntity =  restTemplate.exchange(URL_SERVICE, HttpMethod.PUT, entity, AvatarDto.class, icp);
        if(responseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("avatar to service");
        } else {
            log.info("can't send avatar to service");
        }
    }



//    public byte[] getAvatar(String uuid) {
//        Optional<AvatarDto> avatarData = avatarRepo.findByUuid(uuid);
//        byte[] avatar = AvatarUtils.decompressImage(avatarData.get().getByteSize());
//        return avatar;
//    }
//
//    public AvatarDto getInfoByUuid(String name) {
//        Optional<AvatarDto> avatarDto = avatarRepo.findByUuid(name);

//    public AvatarDto uploadAvatar(MultipartFile file, String uuid) throws IOException {
//
//        if (file.isEmpty()) {
//            throw new NullPointerException("File is empty. Please try again");
//        }
//        AvatarDto avatarDto = avatarRepo.save(AvatarDto.builder()
//                .name(file.getOriginalFilename())
//                .md5(DigestUtils.md5Hex(file.getName()))
//                .fileSize(file.getSize())
//                .byteSize(AvatarUtils.compressImage(file.getBytes())).build());
//        if (avatarDto != null) {
//            return avatarDto;
//        }
//        return null;
//    }


//    public byte[] getAvatar(String uuid) {
//        Optional<AvatarDto> avatarData = avatarRepo.findByUuid(uuid);
//        byte[] avatar = AvatarUtils.decompressImage(avatarData.get().getByteSize());
//        return avatar;
//    }
//
//    public AvatarDto getInfoByUuid(String name) {
//        Optional<AvatarDto> avatarDto = avatarRepo.findByUuid(name);
//
//        return AvatarDto.builder()
//                .uuid(avatarDto.get().getUuid())
//                .name(avatarDto.get().getName())
//                .md5(avatarDto.get().getMd5())
//                .fileSize(avatarDto.get().getFileSize())
//                .byteSize(AvatarUtils.decompressImage(avatarDto.get().getByteSize())).build();
//
//    }

    @Override
    public String getRandomImage() {
        return String.valueOf((int)(Math.random()*5));
    }
}

