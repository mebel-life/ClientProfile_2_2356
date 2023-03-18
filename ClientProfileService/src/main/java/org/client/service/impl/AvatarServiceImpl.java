package org.client.service.impl;

import lombok.AllArgsConstructor;

import org.client.common.dto.AvatarDto;
import org.client.common.dto.IndividualDto;
import org.client.service.AvatarService;
import org.client.service.IndividualService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    private final IndividualService individualService;
    private final RestTemplate restTemplate;

    @Override
    public void addAvatarForClient(String icp) {
        String uuid = individualService.getClient(icp).getUuid();

        //если аватара нет - отправляем запрос в аватарСервис на загрузку аватара, передаем uuidIndividual для которого нужно загрузить аватар
        if(individualService.getClient(icp).getAvatar() == null) {
            HttpHeaders headers = new HttpHeaders();
            //headers.setAccept(Arrays.asList(MediaType.ALL));
            HttpEntity<?> entity = new HttpEntity<>(uuid, headers);
            ResponseEntity<String> responseEntity =  restTemplate.exchange("http://localhost:8888/avatar", HttpMethod.POST, entity, String.class);
            HttpStatus statusCode = responseEntity.getStatusCode();

            //как сделать чтобы в течении какого то времени проверять статус ответа??

            //я вообще правильно запросы отпраляю, или надо через рестконтроллер в package контроллер?

            //получаю ответ от Аватара, засетил поле, а в лоудер что нужно отправить? индивидуал и AvatarDto?
//            if (statusCode == HttpStatus.OK) {
//                IndividualDto dto = individualService.getClient(icp);
//                ResponseEntity<AvatarDto> getAvatar =  restTemplate.getForObject("http://localhost:8888/avatar", AvatarDto, AvatarDto.class);
//                dto.setAvatar(Collections.singleton(getAvatar.getBody()));                         //почему AvatarDto красным подчеркивает??((
//            }

        } else {
            System.out.println("Client have avatar"); // через try catch, ошибку выбрасывать? , это временно
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
        //headers.setAccept(Arrays.asList(MediaType.ALL));
        HttpEntity<AvatarDto> entity = new HttpEntity<>(avatarDto, headers);
        ResponseEntity<?> responseEntity =  restTemplate.exchange("http://localhost:8082/avatar", HttpMethod.POST, entity, String.class);
        HttpStatus statusCode = responseEntity.getStatusCode();
    }
}
