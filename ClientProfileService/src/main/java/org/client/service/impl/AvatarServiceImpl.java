package org.client.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.client.common.dto.AvatarDto;
import org.client.common.dto.IndividualDto;
import org.client.service.AvatarService;
import org.client.service.IndividualService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Slf4j
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    private final IndividualService individualService;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL_AVATAR_SERVICE = "http://localhost:8082/avatar";
    private static final String URL_LOUDER = "http://localhost:8085/avatar";

    @Override
    public void addAvatarForClient(String icp) {
        IndividualDto individualDto = individualService.getClient(icp);
        if (individualDto != null) {
            String uuid = individualDto.getUuid();
            //если аватара нет - отправляем запрос в аватарСервис на загрузку аватара, передаем uuidIndividual для которого нужно загрузить аватар
            if (individualDto.getAvatar() == null) {
                HttpHeaders headers = new HttpHeaders();
                HttpEntity<String> entity = new HttpEntity<>(uuid, headers);
                ResponseEntity<String> responseEntity = restTemplate.exchange(URL_AVATAR_SERVICE, HttpMethod.POST, entity, String.class);
                if (responseEntity.getStatusCode() == HttpStatus.OK) {
                    log.info("avatar upload");
                }
            } else {
                log.info("individualDto has AVATAR");

            }
        } else {
            log.info("individualDto == null");
        }
    }

    @Override
    public AvatarDto getAvatarClient(String icp) {
        return (AvatarDto) Collections.singleton(individualService.getClient(icp).getAvatar());
    }

    @Override
    public void setAvatarForClient(AvatarDto avatarDto) {
        IndividualDto dto = individualService.getClientUuid(avatarDto.getIndividualUuid());
        dto.setAvatar(Collections.singleton(avatarDto));
        //засетил и отправляю в лоудер
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<AvatarDto> entity = new HttpEntity<>(avatarDto, headers);
        ResponseEntity<AvatarDto> responseEntity =  restTemplate.exchange(URL_LOUDER, HttpMethod.POST, entity, AvatarDto.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
    }
}
