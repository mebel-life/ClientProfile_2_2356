package org.client.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.client.common.entity.Contacts.PhoneNumber;
import org.client.repo.PhoneNumberRepo;
import org.client.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    @Autowired
    PhoneNumberRepo phoneNumberRepo;

    public PhoneNumber getByValueUuid(String uuid) {
        PhoneNumber phoneNumber1=phoneNumberRepo.findPhoneNumberByUuid(uuid);
log.info("value " +phoneNumber1);
         return phoneNumber1;

    }
}
