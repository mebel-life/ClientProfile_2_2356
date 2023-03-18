package org.client.service;

import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.client.common.dto.AvatarDto;
import org.client.util.AvatarUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AvatarServiceImp implements AvatarService {

   private final RestTemplate restTemplate;

    @Transactional
    public AvatarDto uploadAvatar(MultipartFile file, String uuid) throws IOException {

        if (file.isEmpty()) {
            throw new NullPointerException("File is empty. Please try again");
        }
        // создал AvatarDTO
        AvatarDto avatarDto = AvatarDto.builder()
                .uuid(UUID.randomUUID().toString())
                .individualUuid(uuid)
                .avatarName(file.getOriginalFilename())
                .md5(DigestUtils.md5Hex(file.getName()))
                .fileSize(file.getSize())
                .byteSize(AvatarUtils.compressImage(file.getBytes())).build();

        // отправляю обратно в мкс Сервис

        HttpHeaders headers = new HttpHeaders();
        //headers.setAccept(Arrays.asList(MediaType.ALL));
        HttpEntity<AvatarDto> entity = new HttpEntity<>(avatarDto, headers);
        ResponseEntity<?> responseEntity =  restTemplate.exchange("http://localhost:8080/avatar/api", HttpMethod.PUT, entity, String.class);
        HttpStatus statusCode = responseEntity.getStatusCode();


        return avatarDto;
    }





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
