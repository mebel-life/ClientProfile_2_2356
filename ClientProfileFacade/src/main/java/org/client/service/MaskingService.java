package org.client.service;


import org.client.common.dto.ContactMediumDto;
import org.client.common.dto.IndividualDto;
import org.client.common.dto.RFPassportDto;

public interface MaskingService {
    String maskTextInfo(String text);

    IndividualDto maskIndividual(IndividualDto individual);
    String maskSurname(String surname);

    String maskFullName(String fullname, String surname);

    ContactMediumDto maskContacts(ContactMediumDto contactMedium);

    RFPassportDto maskRFPassport(RFPassportDto passport);


}
