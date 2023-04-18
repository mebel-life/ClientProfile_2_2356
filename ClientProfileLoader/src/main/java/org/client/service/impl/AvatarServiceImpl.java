package org.client.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.client.common.dto.AvatarDto;
import org.client.common.entity.Avatar;
import org.client.common.entity.Individual;
import org.client.repositories.AvatarRepository;
import org.client.repositories.IndividualRepository;
import org.client.service.AvatarService;
import org.client.util.AvatarUtils;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
@Slf4j
public class AvatarServiceImpl implements AvatarService {
    private final IndividualRepository individualRepository;
    private final AvatarRepository avatarRepository;
    private final AvatarUtils avatarUtils;
    private final IndividualServiceImpl individualService;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String URL_SERVICE = "http://localhost:8080/avatar?icp={icp}";

    @Override
    @Transactional
    public AvatarDto getAvatar(String icp) {
        Individual individual = individualRepository.findByIcp(icp);

        if (individual.getAvatar() == null) {
            //если нет Avatar - отправляем запрос в сервис на загрузку аватара
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<Void> entity = new HttpEntity<>(headers);
            ResponseEntity<AvatarDto> responseEntity =  restTemplate.exchange(URL_SERVICE, HttpMethod.PUT, entity, AvatarDto.class, icp);

            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                log.info("icp send to service");
                log.info("AvatarDto: {}", responseEntity.getBody());
                return responseEntity.getBody();
            } else {
                log.info("can't send icp to service");
                return null;
            }
        } else {
            log.info("avatar received");
            return  avatarUtils.convertToDto(individual.getAvatar());

        }
    }

    @Override
    @Transactional
    public void setAvatar(AvatarDto avatarDto, String icp) {
        Avatar avatar = new Avatar();
        avatar.setByteSize(avatarDto.getByteSize());
        avatar.setUuid(avatarDto.getUuid());
        avatar.setIndividualUuid(avatarDto.getIndividualUuid());
        avatar.setName(avatarDto.getAvatarName());
        avatar.setMd5(avatarDto.getMd5());
        avatar.setFileSize(avatarDto.getFileSize());


        log.info("avatar: {}", avatar);
        avatarRepository.save(avatar);
        avatarRepository.findByUuid(avatar.getUuid());
        log.info("avatar: {}", avatarRepository.findByUuid(avatar.getUuid()));
        Individual individual = individualRepository.findByIcp(icp);
        log.info("individual: {}", individual);
        individual.setAvatar(avatar);
        log.info("individual: {}", individual);
        individualRepository.save(individual);
    }
}
