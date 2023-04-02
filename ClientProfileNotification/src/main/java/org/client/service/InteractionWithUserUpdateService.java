package org.client.service;

import org.client.dto.EmailUpdateDto;
import org.client.dto.PhoneNumberUpdateDto;

public interface InteractionWithUserUpdateService {
    void updatePhoneNumber( PhoneNumberUpdateDto phoneNumberUpdateDto,String icp) throws Exception;

    void updateEmail(EmailUpdateDto emailUpdateDto, String uuid) throws Exception;
}
