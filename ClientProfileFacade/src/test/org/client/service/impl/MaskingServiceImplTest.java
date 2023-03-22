package org.client.service.impl;

import org.client.common.dto.ContactMediumDto;
import org.client.common.dto.EmailDto;
import org.client.common.dto.IndividualDto;
import org.client.common.dto.PhoneNumberDto;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaskingServiceImplTest {

    MaskingServiceImpl m = new MaskingServiceImpl();
    @Test
    void maskIndividual() {
        Collection<ContactMediumDto> contactMediumDtos = List.of(new ContactMediumDto("12321", "12321", List.of(new EmailDto("12332", "1231231","asdsada@gmail.com")), List.of(new PhoneNumberDto("12332", "1231231","+71231231231"))));
        IndividualDto individualDto = IndividualDto.builder().fullName("Asdd Asd Asd").name("Asdd").surname("Asd").patronymic("Asd").contactMedium(contactMediumDtos).build();
        System.out.println(m.maskIndividual(individualDto));
    }
}