package org.client.service;

import org.client.dto.EmailUpdateDto;
import org.client.dto.PhoneNumberUpdateDto;

public interface InteractionWithCPNotification {
    boolean getEmailVerification(EmailUpdateDto emailUpdateDto) throws Exception;
    void getPhoneNumberVerification(PhoneNumberUpdateDto phoneNumberUpdateDto);
}
