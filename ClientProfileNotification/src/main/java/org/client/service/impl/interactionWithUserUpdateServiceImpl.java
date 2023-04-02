package org.client.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.client.dto.EmailUpdateDto;
import org.client.dto.PhoneNumberUpdateDto;
import org.client.service.InteractionWithUserUpdateService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static org.client.util.Generator.generateString;

@AllArgsConstructor
@Service
@Slf4j
public class interactionWithUserUpdateServiceImpl implements InteractionWithUserUpdateService {
    private final RestTemplate restTemplate;
    private static final String CP_UPDATE_SERVICE = "http://localhost:8088/update/individual/phone";
    private static final String CP_UPDATE_SERVICE2 = "http://localhost:8088/update/individual/email";
    private static String verification;

    @Override
    public void updatePhoneNumber(PhoneNumberUpdateDto phoneNumberUpdateDto, String icp) throws Exception {
        log.info("connect to client Update Service");
        ResponseEntity<?> responseEntity = restTemplate.postForEntity(CP_UPDATE_SERVICE, new HttpEntity<>(phoneNumberUpdateDto), PhoneNumberUpdateDto.class);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new Exception();
        } else log.info(verification = generateString() + " код верефикации");
    }

    @Override
    public void updateEmail(EmailUpdateDto emailUpdateDto, String uuid) throws Exception {
        log.info("connect to client Update Service");
        ResponseEntity<?> responseEntity = restTemplate.postForEntity(CP_UPDATE_SERVICE2, new HttpEntity<>(emailUpdateDto), EmailUpdateDto.class);
        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            throw new Exception();
        } else log.info(verification = generateString() + " код верефикации");
    }
}
