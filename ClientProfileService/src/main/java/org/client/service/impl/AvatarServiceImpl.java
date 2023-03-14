package org.client.service.impl;

import lombok.AllArgsConstructor;

import org.client.common.dto.AvatarDto;
import org.client.common.dto.IndividualDto;
import org.client.service.AvatarService;
import org.client.service.IndividualService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@AllArgsConstructor
public class AvatarServiceImpl implements AvatarService {
    private final IndividualService individualService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void addAvatarForClient(String icp) {
        String uuid = individualService.getClient(icp).getUuid();

        //если аватара нет - отправляем запрос в аватарСервис на загрузку аватара
        if(individualService.getClient(icp).getAvatar() == null) {
            ResponseEntity<String> responseEntity =  restTemplate.postForEntity("http://localhost:8888/avatar", uuid, String.class);
            HttpStatus statusCode = responseEntity.getStatusCode();

            //как сделать чтобы в течении какого то времени проверять статус ответа??

            //я вообще правильно запросы отпраляю, или надо через рестконтроллер в package контроллер?

            //получаю ответ от Аватара, засетил поле, а в лоудер что нужно отправить? индивидуал и AvatarDto?
            if (statusCode == HttpStatus.OK) {
                IndividualDto dto = individualService.getClient(icp);
                ResponseEntity<AvatarDto> getAvatar =  restTemplate.getForObject("http://localhost:8888/avatar", AvatarDto, AvatarDto.class);
                dto.setAvatar(Collections.singleton(getAvatar.getBody()));                         //почему AvatarDto красным подчеркивает??((
            }



        } else {
            System.out.println("Client have avatar"); // через try catch, ошибку выбрасывать?
        }
    }

    @Override
    public AvatarDto getAvatarClient(String icp) {
        return (AvatarDto) Collections.singleton(individualService.getClient(icp).getAvatar());
    }
}
