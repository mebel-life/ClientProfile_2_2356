package org.client.service;

import org.client.dto.EmailUpdateDto;
import org.client.dto.IndividualUpdateDto;

import org.client.dto.PhoneNumberUpdateDto;
import org.client.util.ClientProfileLoaderException;

public interface InteractionWithCPLoader {
    void updateIndividual(IndividualUpdateDto userUpdate) throws ClientProfileLoaderException;

    void updatePhoneNumber(PhoneNumberUpdateDto phoneNumberUpdateDto) throws ClientProfileLoaderException;
    void updateEmail(EmailUpdateDto emailUpdateDto) throws ClientProfileLoaderException;
}
