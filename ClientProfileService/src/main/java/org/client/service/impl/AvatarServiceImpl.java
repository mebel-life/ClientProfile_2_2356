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
import org.client.util.ClientNotFoundException;

import java.util.Collections;

@Slf4j
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    private final IndividualService individualService;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL_AVATAR_SERVICE = "http://localhost:8082/avatar?icp={icp}";
    private static final String URL_LOUDER = "http://localhost:8085/api/avatar?icp={icp}";

    @Override
    public AvatarDto addAvatarForClient(String icp) {
        IndividualDto individualDto = individualService.getClientIcp(icp);
        log.info("getClientIcp(" + icp + ") отработал");
        if (individualDto != null) {
            String uuid = individualDto.getUuid();
            //если аватара нет - отправляем запрос в аватарСервис на загрузку аватара, передаем uuidIndividual для которого нужно загрузить аватар
            if (individualDto.getAvatar() == null) {
                HttpHeaders headers = new HttpHeaders();
                HttpEntity<String> entity = new HttpEntity<>(uuid, headers);
                ResponseEntity<AvatarDto> responseEntity = restTemplate.exchange(URL_AVATAR_SERVICE, HttpMethod.POST, entity, AvatarDto.class, icp);
                if (responseEntity.getStatusCode() == HttpStatus.OK) {
                    log.info("avatar upload");
                    return responseEntity.getBody();
                }
            } else {
                log.info("individualDto has AVATAR");
                return null;
            }
        } else {
            log.info("individualDto == null");
            throw new ClientNotFoundException("клиент с Icp = " + icp + " не найден");
        }
        return null;
    }

    @Override
    public AvatarDto getAvatarClient(String icp) {
        return (AvatarDto) Collections.singleton(individualService.getClient(icp).getAvatar());
    }

    @Override
    public void setAvatarForClient(AvatarDto avatarDto, String icp) {
        log.info("avatar пришел в сервис icp = " + icp);
        log.info(avatarDto.toString());

        //отправляю в лоудер, чтобы засетить в таблицу
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<AvatarDto> entity = new HttpEntity<>(avatarDto, headers);
        ResponseEntity<AvatarDto> responseEntity =  restTemplate.exchange(URL_LOUDER, HttpMethod.PUT, entity, AvatarDto.class, icp);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            log.info("avatar ушел в лоудер");
        }
    }
}
