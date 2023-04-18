package org.client.service.impl;

import lombok.AllArgsConstructor;
import org.client.dto.EmailUpdateDto;
import org.client.dto.PhoneNumberUpdateDto;
import org.client.service.InteractionWithCPNotification;
import org.springframework.web.client.RestTemplate;
@AllArgsConstructor
public class InteractionWithCPNotificationImpl implements InteractionWithCPNotification {
    private final RestTemplate restTemplate;
    private static final String CP_LOADER = "http://localhost:9092/api/clients";
    //@Override
    public void generateCode() throws Exception {
        restTemplate.getForEntity(CP_LOADER,String.class);


    }

    @Override
    public boolean getEmailVerification(EmailUpdateDto emailUpdateDto) throws Exception {
        //TODO implement getEmailVerification()
        return false;
    }

    @Override
    public void getPhoneNumberVerification(PhoneNumberUpdateDto phoneNumberUpdateDto) {
        //TODO implement getPhoneNumberVerification()
    }
}
