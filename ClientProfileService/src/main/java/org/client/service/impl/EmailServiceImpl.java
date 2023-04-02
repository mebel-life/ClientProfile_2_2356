package org.client.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.client.common.dto.EmailDto;
import org.client.common.dto.PhoneNumberDto;
import org.client.common.entity.Contacts.Email;
import org.client.repo.EmailRepo;
import org.client.service.EmailService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    EmailRepo emailRepo;
    @Override
    public Email getByValueUuid(String uuid) {
      Email email = emailRepo.findEmailByUuid(uuid);
      return email;

    }








}
